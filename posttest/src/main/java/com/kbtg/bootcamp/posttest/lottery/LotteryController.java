package com.kbtg.bootcamp.posttest.lottery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

