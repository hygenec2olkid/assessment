package com.kbtg.bootcamp.posttest.lottery;

import com.kbtg.bootcamp.posttest.requestbody.TicketDetail;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class LotteryService {
    private final LotteryRepository lotteryRepository;

    public LotteryService(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }
    public Map<String, List<String>> getAllLotteryNumber(){
        List<String> tickets = lotteryRepository.getAllLotteryNumber();
        return Collections.singletonMap("tickets",tickets);
    }

    public Map<String, String> addNewLotteryTicket(TicketDetail ticketDetail) {
        Lottery lottery = new Lottery(ticketDetail.ticket_number(),ticketDetail.amount(),ticketDetail.price());
        this.lotteryRepository.save(lottery);
        return Collections.singletonMap("ticket",ticketDetail.ticket_number());
    }
}
