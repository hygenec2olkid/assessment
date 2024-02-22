package com.kbtg.bootcamp.posttest.lottery;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest({LotteryController.class})
class LotteryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LotteryService lotteryService;
    @Test
    @DisplayName("should get List of lotteries")
    public void testGetAllLotteries() throws Exception {
        Map<String, List<String>> response = new HashMap<>();
        List<String> mockList = Arrays.asList("123456", "000001", "000002");
        response.put("tickets",mockList);

        when(lotteryService.getAllLotteryNumber()).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/lotteries")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(response)));

    }

}