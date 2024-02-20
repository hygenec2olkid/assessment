package com.kbtg.bootcamp.posttest.lottery;

import com.kbtg.bootcamp.posttest.repository.LotteryRepository;
import com.kbtg.bootcamp.posttest.requestbody.TicketDetail;
import com.kbtg.bootcamp.posttest.table.Lottery;
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
        Lottery lottery = new Lottery(ticketDetail.ticket(),ticketDetail.price(),ticketDetail.amount());
        this.lotteryRepository.save(lottery);
        return Collections.singletonMap("ticket",ticketDetail.ticket());
    }
}
