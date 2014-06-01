package ru.astrea.logic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@Entity
@Table (name = "turncategory")
public class TurnCategory implements Serializable {
    private long id;
    private String title;
    private String text;
    private byte[] img;
    private List<Turn> turns;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "turncategory_id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic(fetch=FetchType.LAZY)
    @Lob
    @Column(name = "img")
    public byte[] getImg() {
        return img;
    }


    @Column(name = "title")
    @NotNull(message = "{valid.categoryTitle.NotEmpty}")
    @Size(min=3, max=255, message = "{valid.title.Size}")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setImg(byte[] img) {
        this.img = img;
    }

    @Lob
    @Column(name = "text",  columnDefinition = "TEXT")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TurnCategory() {
    }

    public TurnCategory(int id, String title, byte[] img, String text) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.text = text;
    }


    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "turnCategory")
    public List<Turn> getTurns() {
        return turns;
    }

    public void setTurns(List<Turn> turns) {
        this.turns = turns;
    }



    @Override
    public String toString() {
        return "TurnCategory{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text=" + text +
                '}';
    }
}
