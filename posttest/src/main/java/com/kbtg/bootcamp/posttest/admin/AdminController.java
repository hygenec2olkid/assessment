package com.kbtg.bootcamp.posttest.admin;

import com.kbtg.bootcamp.posttest.exceptionhandler.exception.GetAllLotteryException;
import com.kbtg.bootcamp.posttest.requestbody.TicketDetail;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/lotteries")
    public ResponseEntity<Map<String,String>> addNewLotteryByAdmin(@Valid @RequestBody TicketDetail ticketDetail, BindingResult bindingResult) throws GetAllLotteryException {
        if(bindingResult.hasErrors()){
            throw new GetAllLotteryException("Invalid request body.");
        }
        Map<String, String> response = this.adminService.addNewLotteryByAdmin(ticketDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

