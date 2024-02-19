package com.kbtg.bootcamp.posttest.lottery;

import com.kbtg.bootcamp.posttest.requestbody.TicketDetail;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lotteries")
public class LotteryController {
    @Autowired
    private LotteryService lotteryService;

    @GetMapping("")
    public Map<String, List<String>> getAllLottery(){
        return this.lotteryService.getAllLotteryNumber();
    }

}

