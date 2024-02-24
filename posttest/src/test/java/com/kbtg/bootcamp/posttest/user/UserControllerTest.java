package com.kbtg.bootcamp.posttest.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest({UserController.class})
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {
    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;
    @Test
    @DisplayName("should return status ok after sent request BuyLotteryTicket")
    public void testBuyLotteryTicket() throws Exception {
        int mockUserId = 1234567890;
        int ticketId = 1;

        mockMvc.perform(MockMvcRequestBuilders.post("/users/" + mockUserId + "/lotteries/" + ticketId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    @DisplayName("should return status ok after sent request GetAllLotteryOfUser")
    public void testGetAllLotteryOfUser() throws Exception {
        int mockUserId = 1234567890;
        mockMvc.perform(MockMvcRequestBuilders.get("/users/"+mockUserId +"/lotteries"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    @DisplayName("should return status ok after sent request SellLotteryFromUser")
    public void testSellLotteryFromUser() throws Exception {
        int mockUserId = 1234567890;
        int mockTicketId = 1;
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/"+mockUserId +"/lotteries/"+mockTicketId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    }

