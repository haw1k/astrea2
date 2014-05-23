package ru.astrea.logic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;


@Entity
@Table (name = "turncategory")
public class TurnCategory implements Serializable {
    private long id;

    @NotNull(message = "{valid.categoryTitle.NotEmpty}")
    @Size(min=3, max=255, message = "{valid.title.Size}")
    private String title;

    private byte[] img;
    private Set<Turn> turns;


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
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setImg(byte[] img) {
        this.img = img;
    }

    public TurnCategory() {
    }

    public TurnCategory(int id, String title, byte[] img) {
        this.id = id;
        this.title = title;
        this.img = img;
    }


    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "turnCategory")
    public Set<Turn> getTurns() {
        return turns;
    }

    public void setTurns(Set<Turn> turns) {
        this.turns = turns;
    }


    @Override
    public String toString() {
        return "TurnCategory{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", img=" + Arrays.toString(img) +
//                ", turns=" + turns +
                '}';
    }
}
