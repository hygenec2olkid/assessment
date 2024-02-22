package com.kbtg.bootcamp.posttest.lottery;

import com.kbtg.bootcamp.posttest.repository.LotteryRepository;
import com.kbtg.bootcamp.posttest.requestbody.TicketDetail;
import com.kbtg.bootcamp.posttest.table.Lottery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
class LotteryServiceTest {
    private LotteryRepository lotteryRepository;
    private LotteryService lotteryService;

    @BeforeEach
    void setUp() {
        lotteryRepository = mock(LotteryRepository.class);
        lotteryService = new LotteryService(lotteryRepository);
    }

    @Test
    @DisplayName(value = "should return List of tickets")
    public void testGetAllLotteryNumber(){
        List<String> mockList = Arrays.asList("123456", "000001", "000002");
        when(lotteryRepository.getAllLotteryNumber()).thenReturn(mockList);
        Map<String,List<String>> expected = Collections.singletonMap("tickets",lotteryRepository.getAllLotteryNumber());
        LotteryService lotteryService = new LotteryService(lotteryRepository);

        Map<String, List<String>> actual = lotteryService.getAllLotteryNumber();

        assertEquals(expected,actual);
    }
    @Test
    @DisplayName(value = "should return ticketNumer after addNewLotteryTicket")
    public void testAddNewLotteryTicket(){
        Lottery mockLottery = new Lottery("123456",80,1);
        TicketDetail mockTicketDetail = new TicketDetail("123456",80,1);
        Map<String,String> expected = Collections.singletonMap("ticket", mockLottery.getTicket_number());
        when(lotteryRepository.save(mockLottery)).thenReturn(mockLottery);

        Map<String,String> actual = lotteryService.addNewLotteryTicket(mockTicketDetail);

        assertEquals(expected,actual);
    }

}