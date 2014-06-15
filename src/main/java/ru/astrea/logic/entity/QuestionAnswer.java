package ru.astrea.logic.entity;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "question_answer")
public class QuestionAnswer implements Serializable {
    private long id;
    private String name;
    private String email;
    private String phone;
    private String question;
    private String answer;
    private LocalDate creationDate;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_answer_id")
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

    @Column(name = "question", length = 5000)
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    @Column(name = "answer", length = 2000)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

    public QuestionAnswer(long id, String name, String email, String phone, String question, String answer, LocalDate creationDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.question = question;
        this.answer = answer;
        this.creationDate = creationDate;
    }

    public QuestionAnswer() {
    }

    @Override
    public String toString() {
        return "QuestionAnswer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
