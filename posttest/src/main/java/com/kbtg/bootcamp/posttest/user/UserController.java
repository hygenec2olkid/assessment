package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.exceptionhandler.LotteryDeleteException;
import com.kbtg.bootcamp.posttest.exceptionhandler.LotteryPurchaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/{userId}/lotteries/{ticketId}")
    Map<String, String> buyLotteryTicket(@PathVariable Integer userId,@PathVariable Integer ticketId) throws LotteryPurchaseException {
        return this.userService.buyLotteryTicket(userId,ticketId);
    }

    @GetMapping("/{userId}/lotteries")
    Map<String, Object> getListAllLotteryTicket(@PathVariable Integer userId){
        return this.userService.getListAllLotteryTicket(userId);
    }

    @DeleteMapping("/{userId}/lotteries/{ticketId}")
    Map<String, String> sellLotteryTicket(@PathVariable Integer userId,@PathVariable Integer ticketId) throws LotteryDeleteException {
        return this.userService.sellLotteryTicket(userId,ticketId);
    }


}
