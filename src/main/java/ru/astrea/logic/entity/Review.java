package ru.astrea.logic.entity;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "review")
public class Review implements Serializable {
    private long id;
    private String name;
    private String review;
    private LocalDate creationDate;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "review_id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "review", length = 5000)
    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Column(name = "creation_date")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Transient
    public String getCreationDateString() {
        String creationDateString = "";
        if(creationDate != null) {
            creationDateString = org.joda.time.format.DateTimeFormat.forPattern("yyyy-MM-dd").print(creationDate);

        }
        return creationDateString;
    }

    public Review(long id, String name, String review, LocalDate creationDate) {
        this.id = id;
        this.name = name;
        this.review = review;
        this.creationDate = creationDate;
    }

    public Review() {
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reviews='" + review + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
