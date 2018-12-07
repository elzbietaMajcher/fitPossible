package pl.sda.fitpossible.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.fitpossible.entity.ActivityHistory;
import pl.sda.fitpossible.entity.AppUser;

import java.util.List;
import java.util.Optional;

public interface ActivityHistoryRepository extends JpaRepository<ActivityHistory, Long> {
    List<ActivityHistory> findAllByUser(AppUser owner);

    List<ActivityHistory> findAllByUserLogin(String login);

    /*Optional<ActivityHistory> findById(Long id);*/
}
