package com.kbtg.bootcamp.posttest.admin;

import com.kbtg.bootcamp.posttest.exceptionhandler.exception.GetAllLotteryException;
import com.kbtg.bootcamp.posttest.lottery.LotteryService;
import com.kbtg.bootcamp.posttest.requestbody.TicketDetail;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private  LotteryService lotteryService;

    @PostMapping("/lotteries")
    public Map<String,String> addNewLotteryTicket(@Valid @RequestBody TicketDetail ticketDetail, BindingResult bindingResult) throws GetAllLotteryException {
        if(bindingResult.hasErrors()){
            throw new GetAllLotteryException("Invalid request body");
        }
        return this.lotteryService.addNewLotteryTicket(ticketDetail);
    }
}

