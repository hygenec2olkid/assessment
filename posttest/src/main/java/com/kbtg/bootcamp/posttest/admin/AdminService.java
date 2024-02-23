package com.kbtg.bootcamp.posttest.admin;

import com.kbtg.bootcamp.posttest.repository.LotteryRepository;
import com.kbtg.bootcamp.posttest.requestbody.TicketDetail;
import com.kbtg.bootcamp.posttest.table.Lottery;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class AdminService {
    private final LotteryRepository lotteryRepository;

    public AdminService(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }
    public Map<String, String> addNewLotteryByAdmin(TicketDetail ticketDetail) {
        Lottery lottery = new Lottery(ticketDetail.ticket(),ticketDetail.price(),ticketDetail.amount());
        this.lotteryRepository.save(lottery);
        return Collections.singletonMap("ticket",ticketDetail.ticket());
    }
}
