package pl.sda.fitpossible.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.fitpossible.entity.ActivityHistory;

public interface ActivityHistoryRepository extends JpaRepository<ActivityHistory,Long> {
}
