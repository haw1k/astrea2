package ru.astrea.logic.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Table(name="turnprice")
@Entity
public class TurnPrice implements Serializable{
    private Long id;
    private String turn;
    private String price;

    public TurnPrice(Long id, String title, String price) {
        this.id = id;
        this.turn = title;
        this.price = price;
    }

    public TurnPrice() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "turn_price_id")
    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Size(min=3, max=255, message = "{valid.turnTitle.Size}")
    @Column(name="turn")
    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    @Column(name="price")
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
