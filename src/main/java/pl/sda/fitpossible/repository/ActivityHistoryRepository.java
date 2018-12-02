package pl.sda.fitpossible.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.fitpossible.entity.ActivityHistory;
import pl.sda.fitpossible.entity.AppUser;

import java.util.List;

public interface ActivityHistoryRepository extends JpaRepository<ActivityHistory,Long> {
    List<ActivityHistory> findAllByUser(AppUser owner);
}
