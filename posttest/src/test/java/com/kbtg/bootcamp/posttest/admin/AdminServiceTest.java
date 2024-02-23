package com.kbtg.bootcamp.posttest.admin;

import com.kbtg.bootcamp.posttest.repository.LotteryRepository;
import com.kbtg.bootcamp.posttest.requestbody.TicketDetail;
import com.kbtg.bootcamp.posttest.table.Lottery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AdminServiceTest {
    private LotteryRepository lotteryRepository;
    private AdminService adminService;

    @BeforeEach
    void setUp() {
        lotteryRepository = mock(LotteryRepository.class);
        adminService = new AdminService(lotteryRepository);
    }

    @Test
    @DisplayName(value = "should return ticketNumber after addNewLotteryTicket")

    void addNewLotteryByAdmin() {
            Lottery mockLottery = new Lottery("123456",80,1);
            TicketDetail mockTicketDetail = new TicketDetail("123456",80,1);
            Map<String,String> expected = Collections.singletonMap("ticket", mockLottery.getTicket_number());
            when(lotteryRepository.save(mockLottery)).thenReturn(mockLottery);

            Map<String,String> actual = adminService.addNewLotteryByAdmin(mockTicketDetail);

            assertEquals(expected,actual);
    }
}