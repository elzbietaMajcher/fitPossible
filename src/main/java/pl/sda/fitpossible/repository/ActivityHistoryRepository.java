package pl.sda.fitpossible.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.fitpossible.dto.ActivityHistoryDto;
import pl.sda.fitpossible.entity.ActivityHistory;
import pl.sda.fitpossible.entity.AppUser;

import java.time.LocalDateTime;
import java.util.List;

public interface ActivityHistoryRepository extends JpaRepository<ActivityHistory, Long> {
    List<ActivityHistory> findAllByUser(AppUser owner);

    List<ActivityHistory> findAllByUserLogin(String login);

    List<ActivityHistory> findAllByUserIdAndStartTimeAfter(Long userId, LocalDateTime localDateTime);


    /*Optional<ActivityHistory> findById(Long id);*/
}
