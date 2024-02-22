package com.kbtg.bootcamp.posttest.admin;

import com.kbtg.bootcamp.posttest.lottery.LotteryService;
import com.kbtg.bootcamp.posttest.requestbody.TicketDetail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.BindingResult;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;



@WebMvcTest({AdminController.class})
@AutoConfigureMockMvc(addFilters = false)
class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LotteryService lotteryService;

    private String getAuthorizationHeader(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
        return new String(encodedAuth);
    }

    @Test
    @DisplayName("should return ticketNumber after add into repo")
    public void testAddNewTicketSuccess() throws Exception {
        Map<String, String> response = new HashMap<>();
        response.put("ticket","123456");
        String requestBody = """
                {
                    "ticket": "123456",
                    "price": "80",
                    "amount": "1"
                }""";
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(lotteryService.addNewLotteryTicket(any(TicketDetail.class))).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/lotteries")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .header("Authorization", "Basic " + getAuthorizationHeader("admin", "password")))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.ticket").value("123456"));
    }

    @Test
    @DisplayName("should throw GetAllLotteryException")
    public void testThrowAllLotteryException() throws Exception {
        String requestBody = """
                {
                    "ticket": "12345612341234",
                    "price": "80",
                    "amount": "1"
                }""";
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/admin/lotteries")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .header("Authorization", "Basic " + getAuthorizationHeader("admin", "password")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").value("Invalid request body"));
    }

}
