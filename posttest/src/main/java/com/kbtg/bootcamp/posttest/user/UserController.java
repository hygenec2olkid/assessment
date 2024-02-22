package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.exceptionhandler.exception.LotteryDeleteException;
import com.kbtg.bootcamp.posttest.exceptionhandler.exception.LotteryIdNotFound;
import com.kbtg.bootcamp.posttest.exceptionhandler.exception.LotteryPurchaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/{userId}/lotteries/{ticketId}")
    Map<String, String> butNewLottery(@PathVariable Integer userId, @PathVariable Integer ticketId) throws LotteryPurchaseException {
        return this.userService.buyLotteryTicket(userId,ticketId);
    }

    @GetMapping("/{userId}/lotteries")
    Map<String, Object> getAllLotteryOfUser(@PathVariable Integer userId) throws LotteryIdNotFound {
        return this.userService.getListAllLotteryTicket(userId);
    }

    @DeleteMapping("/{userId}/lotteries/{ticketId}")
    Map<String, String> sellLotteryFromUser(@PathVariable Integer userId, @PathVariable Integer ticketId) throws LotteryDeleteException {
        return this.userService.sellLotteryTicket(userId,ticketId);
    }


}
