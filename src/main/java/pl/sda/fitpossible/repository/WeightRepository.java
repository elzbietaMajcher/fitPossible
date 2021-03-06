package pl.sda.fitpossible.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.fitpossible.dto.AppUserDto;
import pl.sda.fitpossible.entity.AppUser;
import pl.sda.fitpossible.entity.Weight;

import java.util.List;

public interface WeightRepository extends JpaRepository<Weight, Long> {
    List<Weight>findAllByUserLogin(String login);
   //List<Weight>findAllByOwner(AppUser owner);

}
