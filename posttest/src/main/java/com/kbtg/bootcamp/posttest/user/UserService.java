package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.exceptionhandler.exception.LotteryDeleteException;
import com.kbtg.bootcamp.posttest.exceptionhandler.exception.LotteryIdNotFound;
import com.kbtg.bootcamp.posttest.exceptionhandler.exception.LotteryPurchaseException;
import com.kbtg.bootcamp.posttest.repository.LotteryRepository;
import com.kbtg.bootcamp.posttest.repository.UserTicketRepository;
import com.kbtg.bootcamp.posttest.table.Lottery;
import com.kbtg.bootcamp.posttest.table.UserTicket;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final LotteryRepository lotteryRepository;

    private final UserTicketRepository userTicketRepository;

    public UserService(LotteryRepository lotteryRepository, UserTicketRepository userTicketRepository) {
        this.lotteryRepository = lotteryRepository;
        this.userTicketRepository = userTicketRepository;
    }

    public Map<String,String> buyLotteryTicket(String userId, Integer ticketId) throws LotteryPurchaseException {
        Lottery lottery = this.lotteryRepository.getAllLotteryById(ticketId);
        if (lottery != null){
            UserTicket userTicket = new UserTicket(userId,lottery.getId(),lottery.getTicket_number(),lottery.getPrice(),1);
            UserTicket saveTicket = this.userTicketRepository.save(userTicket);


            updateLotteryAfterBuy(lottery);
            return Collections.singletonMap("id",String.valueOf(saveTicket.getId()));
        }throw new LotteryPurchaseException("ticketId: " + ticketId + " not have in repository.");
    }
    public void updateLotteryAfterBuy(Lottery lottery){
        lottery.setAmount(lottery.getAmount() - 1);
        if (lottery.getAmount() < 0 || lottery.getAmount() == 0){
            this.lotteryRepository.delete(lottery);
        } else{
            this.lotteryRepository.save(lottery);
        }
    }

    public Map<String,Object> getListAllLotteryTicket(String userId) throws LotteryIdNotFound {
            List<String> lst = this.userTicketRepository.getListLotteriesByUserId(userId);
            if (!lst.isEmpty()){
                Integer count = calTotalCount(lst);
                Integer cost = calTotalCost(lst);
                Map<String,Object> response= new HashMap<>();
                response.put("tickets",cutStringList(lst));
                response.put("count",count);
                response.put("cost",cost);
                return response;
            }throw new LotteryIdNotFound("UserId: " + userId + " has not bought a lottery ticket yet.");
    }

    public Integer calTotalCount(List<String> lst) {
        int sumCount = 0;
        for(String lottery : lst){
            String[] c = lottery.split(",");
            sumCount += Integer.parseInt(c[2]);
        }
        return sumCount;
    }

    public Integer calTotalCost(List<String> lst){
        int sumCount = 0;
        for(String lottery : lst){
            String[] c = lottery.split(",");
            sumCount += Integer.parseInt(c[1]) * Integer.parseInt(c[2]);
        }
        return sumCount;

    }
    public List<String> cutStringList(List<String> lst){
        List<String> lottery_list = new ArrayList<>();
        for(String lottery : lst){
            String[] c = lottery.split(",");
            lottery_list.add(c[0]);
        }
        return lottery_list;
    }

    public Map<String, String> sellLotteryTicket(String userId, Integer ticketId) throws LotteryDeleteException {
        UserTicket userTicket = this.userTicketRepository.sellLotteryTicket(userId,ticketId, Limit.of(1));

        if (userTicket != null){
            String ticketNumber = userTicket.getTicket_number();
            this.userTicketRepository.delete(userTicket);
            return Collections.singletonMap("ticket",ticketNumber);
        }

        throw new LotteryDeleteException("UserId: " + userId + " not have ticketId: " + ticketId);
    }
}
