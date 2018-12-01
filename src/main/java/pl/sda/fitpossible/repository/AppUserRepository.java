package pl.sda.fitpossible.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.fitpossible.entity.AppUser;

@Repository  //??
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
