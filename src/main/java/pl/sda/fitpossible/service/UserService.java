package pl.sda.fitpossible.service;

import org.springframework.stereotype.Service;
import pl.sda.fitpossible.dto.UserDto;
import pl.sda.fitpossible.entity.User;
import pl.sda.fitpossible.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public void create(UserDto dto) {
        User user = mapTo(dto);
        userRepository.save(user);
    }

    public UserDto findUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
        return mapTo(user);
    }

    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapTo).collect(Collectors.toList());
    }

    public void update(Long id, UserDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
        user.setPassword(dto.getPassword());
        user.setLifestyle(dto.getLifestyle());
        user.setEmail(dto.getEmail());
        userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private UserDto mapTo(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setLogin(user.getLogin());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        dto.setDateOfBirth(user.getDateOfBirth());
        dto.setGender(user.getGender());
        dto.setHeight(user.getHeight());
        dto.setLifestyle(user.getLifestyle());
        return dto;
    }

    private User mapTo(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setDateOfBirth(dto.getDateOfBirth());
        user.setGender(dto.getGender());
        user.setHeight(dto.getHeight());
        user.setLifestyle(dto.getLifestyle());
        return user;
    }
}
