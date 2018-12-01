package pl.sda.fitpossible.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.fitpossible.entity.User;

@Repository  //??
public interface UserRepository extends JpaRepository<User, Long> {
}
