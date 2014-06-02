package ru.astrea.logic.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Entity
@Table(name = "user")
public class User implements Serializable {
    private Long id;
    private String username;
    private String password;
    private List<Role> roles;
    private boolean enabled;

    @Column(name="enabled")
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToMany
    @JoinTable
    @NotNull(message = "{valid.user.role.NotEmpty}")
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Size(min=3,  message = "{valid.username.Size}")
//    @UniqueUsername(message = "{valid.username.Unique}")
//    @Username(message = "Логин может состоять из букв, цифр, дефисов и подчёркиваний. Длина от 3 до 16 символов.")
    @Column(name = "username", unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Size(min=5, message = "{valid.password.Size}")
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(Long id, String username, String password, List<Role> roles, boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.enabled = enabled;
    }

    public User() {
    }

    @Transient
    public String getRolesString() {
        String rolesString = "";
        StringBuilder builder = new StringBuilder();
        if(roles != null) {
            for (Role role : roles) {
                if(roles.lastIndexOf(role) != (roles.size()-1)) {
                    builder.append(role.getRolename());
                    builder.append(",");
                }
                else {
                    builder.append(role.getRolename());
                }
            }
            rolesString = builder.toString();
        }
        return rolesString;
    }

}
