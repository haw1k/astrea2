package ru.astrea.logic.entity;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import java.io.Serializable;

@Entity
@Table(name="consultation")
public class Consultation implements Serializable {
    private long id;
    private String contact;
    private String email;
    private String phone;
    private String description;
    private LocalDate creationDate;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "consultation_id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Min(1)
    @Column(name = "contact")
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Email
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "creation_date")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @Past(message = "Не корректная дата")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
