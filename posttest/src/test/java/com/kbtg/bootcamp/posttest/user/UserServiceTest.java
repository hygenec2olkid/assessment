package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.exceptionhandler.exception.LotteryDeleteException;
import com.kbtg.bootcamp.posttest.exceptionhandler.exception.LotteryIdNotFound;
import com.kbtg.bootcamp.posttest.exceptionhandler.exception.LotteryPurchaseException;
import com.kbtg.bootcamp.posttest.repository.LotteryRepository;
import com.kbtg.bootcamp.posttest.repository.UserTicketRepository;
import com.kbtg.bootcamp.posttest.table.Lottery;
import com.kbtg.bootcamp.posttest.table.UserTicket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private LotteryRepository lotteryRepository;

    private UserTicketRepository userTicketRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        lotteryRepository = mock(LotteryRepository.class);
        userTicketRepository = mock(UserTicketRepository.class);
        userService = new UserService(lotteryRepository,userTicketRepository);
    }

    @Test
    @DisplayName("should return user_ticket id after buy")
    void buyLotteryTicket() throws LotteryPurchaseException {
        int mockUserId = 1;
        int mockTicketId = 1;
        Lottery mockLottery = new Lottery("123456",80,1);
        when(lotteryRepository.getAllLotteryById(any())).thenReturn(mockLottery);
        UserTicket mockUserTicket = new UserTicket(
                mockUserId,
                mockLottery.getId(),
                mockLottery.getTicket_number(),
                mockLottery.getPrice(),
                mockLottery.getAmount());
        when(userTicketRepository.save(any())).thenReturn(mockUserTicket);
        UserTicket mockSaveTicket = userTicketRepository.save(mockUserTicket);
        Map<String,String> expected = Collections.singletonMap("id", String.valueOf(mockSaveTicket.getId()));

        Map<String,String> actual = userService.buyLotteryTicket(mockUserId,mockTicketId);

        assertEquals(expected,actual);
    }
    @Test
    @DisplayName("should throw LotteryPurchaseException when mockLottery is null")
    public void testThrowLotteryPurchaseException()  {
        int mockUserId = 1;
        int mockTicketId = 1;
        when(lotteryRepository.getAllLotteryById(any())).thenReturn(null);
        String expected = "ticketId: " + mockTicketId + " not have in repository";

        Exception actual = assertThrows(LotteryPurchaseException.class, () -> userService.buyLotteryTicket(mockUserId, mockTicketId));

        assertEquals(expected,actual.getMessage());
    }

    @Test
    @DisplayName("should return getListAllLotteryTicket")
    public void getListAllLotteryTicket() throws LotteryIdNotFound {
        int mockUserId = 1;
        when(userTicketRepository.getListLotteriesByUserId(mockUserId)).thenReturn(Arrays.asList("123456,80,1","000001,80,1","000002,80,1"));
        Map<String,Object> expected= new HashMap<>();
        expected.put("tickets",Arrays.asList("123456","000001","000002"));
        expected.put("count",3);
        expected.put("cost",240);

        Map<String,Object> actual = userService.getListAllLotteryTicket(mockUserId);

        assertEquals(expected,actual);
    }
    @Test
    @DisplayName("should throw LotteryIdNotFound")
    public void testThrowLotteryIdNotFound()  {
        int mockUserId = 1;
        when(userTicketRepository.getListLotteriesByUserId(mockUserId)).thenReturn(List.of());
        String expected = "UserId: " + mockUserId + " not found";

        Exception actual = assertThrows(LotteryIdNotFound.class, () -> userService.getListAllLotteryTicket(mockUserId));

        assertEquals(expected,actual.getMessage());
    }

    @Test
    @DisplayName("should return ticketNumber after delete ticket")
    public void sellLotteryTicket() throws LotteryDeleteException {
        int mockUserId = 1;
        int mockTicketId = 1;
        UserTicket mockUserTicket = new UserTicket(mockUserId,mockTicketId,"123456",80,1);
        when(userTicketRepository.sellLotteryTicket(mockUserId,mockTicketId)).thenReturn(mockUserTicket);
        String mockTicketNumber = mockUserTicket.getTicket_number();
        Map<String, String> expected = Collections.singletonMap("ticket",mockTicketNumber);

        Map<String, String> actual = userService.sellLotteryTicket(mockUserId,mockTicketId);

        assertEquals(expected,actual);

    }
    @Test
    @DisplayName("should throw LotteryDeleteException")
    public void testThrowLotteryDeleteException()  {
        int mockUserId = 1;
        int mockTicketId = 1;
        when(userTicketRepository.sellLotteryTicket(mockUserId,mockTicketId)).thenReturn(null);
        String expected = "Delete fail please check userId and ticketId have in repository";

        Exception actual = assertThrows(LotteryDeleteException.class, () -> userService.sellLotteryTicket(mockUserId,mockTicketId));

        assertEquals(expected,actual.getMessage());
    }
    @Test
    @DisplayName("should save after updateLotteryAfterBuy call")
    public void updateLotteryAfterBuy(){
        Lottery mockLottery = new Lottery("123456",80,2);

        userService.updateLotteryAfterBuy(mockLottery);

        verify(lotteryRepository,times(1)).save(mockLottery);
    }
    @Test
    @DisplayName("should delete after updateLotteryAfterBuy call")
    public void deleteLotteryAfterBuy(){
        Lottery mockLottery = new Lottery("123456",80,1);

        userService.updateLotteryAfterBuy(mockLottery);

        verify(lotteryRepository,times(1)).delete(mockLottery);
    }

    @Test
    @DisplayName("should return total of count")
    public void testCalTotalCount(){
        int expected = 4;
        List<String> lst = Arrays.asList("123456,80,2","000001,100,1","000002,110,1");

        int actual = userService.calTotalCount(lst);

        assertEquals(expected,actual);
    }
    @Test
    @DisplayName("should return total of cost")
    public void testCalTotalCost(){
        int expected = 370;
        List<String> lst = Arrays.asList("123456,80,2","000001,100,1","000002,110,1");

        int actual = userService.calTotalCost(lst);

        assertEquals(expected,actual);
    }
    @Test
    @DisplayName("should return list ticket_number")
    public void testCutStringList(){
        List<String> expected = Arrays.asList("123456","000001","000002");
        List<String> lst = Arrays.asList("123456,80,2","000001,100,1","000002,110,1");

        List<String> actual = userService.cutStringList(lst);

        assertEquals(expected,actual);
    }
}