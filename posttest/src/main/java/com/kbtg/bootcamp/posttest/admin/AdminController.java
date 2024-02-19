package com.kbtg.bootcamp.posttest.admin;

import com.kbtg.bootcamp.posttest.lottery.LotteryService;
import com.kbtg.bootcamp.posttest.requestbody.TicketDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AdminController {
    @Autowired
    private  LotteryService lotteryService;

    @PostMapping("/admin/lotteries")
    public Map<String,String> addNewLotteryTicket(@Validated @RequestBody TicketDetail ticketDetail){
        return this.lotteryService.addNewLotteryTicket(ticketDetail);
    }
}

