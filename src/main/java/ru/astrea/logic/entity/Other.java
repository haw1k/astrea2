package ru.astrea.logic.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "other")
public class Other implements Serializable {
    private long id;
    private String about;
    private String address;
    private String worktime;
    private String workphone;
    private String mobilephone;
    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "other_id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Lob
    @Column(name = "about",  columnDefinition = "TEXT")
    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "worktime")
    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    @Column(name = "workphone")
    public String getWorkphone() {
        return workphone;
    }

    public void setWorkphone(String workphone) {
        this.workphone = workphone;
    }

    @Column(name = "mobilephone")
    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Other(long id, String about, String address, String worktime, String workphone, String mobilephone, String email) {
        this.id = id;
        this.about = about;
        this.address = address;
        this.worktime = worktime;
        this.workphone = workphone;
        this.mobilephone = mobilephone;
        this.email = email;
    }

    public Other() {
    }

    @Override
    public String toString() {
        return "Other{" +
                "id=" + id +
                ", about='" + about + '\'' +
                ", address='" + address + '\'' +
                ", worktime='" + worktime + '\'' +
                ", workphone='" + workphone + '\'' +
                ", mobilephone='" + mobilephone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
