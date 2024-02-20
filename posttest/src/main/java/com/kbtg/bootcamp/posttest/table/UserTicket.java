package com.kbtg.bootcamp.posttest.table;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_ticket")
public class UserTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer user_id;
    private Integer ticket_id;
    private String  ticket_number;
    private Integer price;
    private Integer amount;

    public UserTicket(Integer user_id, Integer ticket_id, String ticket_number, Integer price, Integer amount) {
        this.user_id = user_id;
        this.ticket_id = ticket_id;
        this.ticket_number = ticket_number;
        this.price = price;
        this.amount = amount;
    }

    public UserTicket() {
    }
}
