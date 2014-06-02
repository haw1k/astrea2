package ru.astrea.logic.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Entity
@Table (name = "turn")
public class Turn implements Serializable {
    private Long id;
    private String title;
    private String text;
    private LocalDate creationDate;
    private TurnCategory turnCategory;

    @Column(name = "creation_date")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "turn_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "title")
    @Size(min=3, message = "{valid.turnTitle.Size}")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Lob
    @Column(name = "text",  columnDefinition = "TEXT")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="turncategory_id")
    @NotNull(message = "{valid.turnCategory.NotEmpty}")
    public TurnCategory getTurnCategory() {
        return turnCategory;
    }

    public void setTurnCategory(TurnCategory turnCategory) {
        this.turnCategory = turnCategory;
    }

    public Turn(String title, Long id, String text, LocalDate creationDate, TurnCategory turnCategory) {
        this.title = title;
        this.id = id;
        this.text = text;
        this.creationDate = creationDate;
        this.turnCategory = turnCategory;
    }

    @Transient
    public String getCreationDateString() {
        String creationDateString = "";
        if(creationDate != null) {
            creationDateString = org.joda.time.format.DateTimeFormat.forPattern("yyyy-MM-dd").print(creationDate);

        }
        return creationDateString;
    }

    public Turn() {
    }

    @Override
    public String toString() {
        return "Turn{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", creationDate=" + creationDate +
                ", turnCategory=" + turnCategory +
                '}';
    }
}
