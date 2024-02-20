package com.kbtg.bootcamp.posttest.repository;
import com.kbtg.bootcamp.posttest.table.Lottery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LotteryRepository extends JpaRepository<Lottery,Long> {
    @Query("SELECT ticket_number FROM Lottery")
    List<String> getAllLotteryNumber();

    @Query(value = "SELECT u FROM Lottery u WHERE u.id = :userId")
    Lottery getAllLotteryById(@Param("userId") Integer userId);

}
