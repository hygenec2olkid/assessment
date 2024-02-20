package com.kbtg.bootcamp.posttest.repository;
import com.kbtg.bootcamp.posttest.table.UserTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserTicketRepository extends JpaRepository<UserTicket,Long> {
    @Query("SELECT u.ticket_number,u.price,u.amount FROM UserTicket u WHERE u.user_id = :userId")
    List<String> getListLotteriesByUserId(@Param("userId") Integer userId);

    @Query("SELECT u FROM UserTicket u WHERE u.user_id = :userId AND u.ticket_id = :ticketId")
    UserTicket sellLotteryTicket(
            @Param("userId")
            Integer userId,
            @Param("ticketId")
            Integer ticketId);
}
