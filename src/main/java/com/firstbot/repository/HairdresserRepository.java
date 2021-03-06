package com.firstbot.repository;

import com.firstbot.entity.Hairdresser;
import com.firstbot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
public interface HairdresserRepository extends JpaRepository<Hairdresser,Long> {
     List<Hairdresser> findAll();
     List<Hairdresser> findByDayHairCut(String dayHairCut);
     @Query("SELECT f FROM Hairdresser f where TIMESTAMPDIFF(HOUR,CURRENT_TIMESTAMP(),f.dateHairCut) < 1")
     List<Hairdresser> findReminderDateHairCut();
     @Modifying
     @Query("update Hairdresser h set h.reminder = ?1 where h.user = ?2 and h.dateHairCut = ?3")
     void updateReminder(boolean reminder,User user,LocalDateTime dateHairCut);
}
