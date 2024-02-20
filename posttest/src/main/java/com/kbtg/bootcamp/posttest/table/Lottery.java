package com.kbtg.bootcamp.posttest.table;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "lottery")
public class Lottery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ticket_number;
    private Integer price;
    private Integer amount;

    public Lottery(String ticket_number,Integer price,Integer amount) {
        this.ticket_number = ticket_number;
        this.price = price;
        this.amount = amount;
    }

    public Lottery() {
    }
}

