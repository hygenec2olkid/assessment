package com.kbtg.bootcamp.posttest.lottery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LotteryRepository extends JpaRepository<Lottery,Long> {
    @Query("SELECT ticket_number FROM Lottery")
    List<String> getAllLotteryNumber();
}
