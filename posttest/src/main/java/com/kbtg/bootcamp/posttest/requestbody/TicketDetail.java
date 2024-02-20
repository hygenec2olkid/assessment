package com.kbtg.bootcamp.posttest.requestbody;


import jakarta.validation.constraints.*;

public record TicketDetail(
        @NotNull
        @Pattern(regexp = "\\d+", message = "Lottery should have only number")
        @Size(min = 6,max = 6, message = "Lottery should have 6 character")
        String ticket,
        @NotNull
        @Positive(message = "Price should more than 0")
        Integer price,
        @NotNull
        @Positive(message = "Amount should more than 0")
        Integer amount){}
