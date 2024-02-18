package com.kbtg.bootcamp.posttest.lottery;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class LotteryController {
    private final LotteryService lotteryService;
    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @GetMapping("/lotteries")
    public Map<String, List<String>> getAllLottery(){
        return this.lotteryService.getAllLotteryNumber();
    }

    @PostMapping("/admin/lotteries")
    public Map<String,String> addNewLotteryTicket(@RequestBody TicketDetail ticketDetail){
        return this.lotteryService.addNewLotteryTicket(ticketDetail);
    }

}
record TicketDetail(
        String ticket,
        Integer price,
        Integer amount){}
