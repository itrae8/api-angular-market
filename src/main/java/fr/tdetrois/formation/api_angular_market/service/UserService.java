package fr.tdetrois.formation.api_angular_market.service;

import fr.tdetrois.formation.api_angular_market.model.User;
import fr.tdetrois.formation.api_angular_market.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User addUser(User user) {
        User userToUpdate = new User();

        userToUpdate.setFirstname(user.getFirstname());
        userToUpdate.setLastname(user.getLastname());
        userToUpdate.setAge(user.getAge());
        userToUpdate.setLogin(user.getLogin());
        userToUpdate.setPassword(user.getPassword());

        userRepository.save(userToUpdate);
        return userToUpdate;
    }

    @Transactional
    public User modifyUser(Integer id, User user) {
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User userToUpdate = optUser.get();

        userToUpdate.setFirstname(user.getFirstname());
        userToUpdate.setLastname(user.getLastname());
        userToUpdate.setAge(user.getAge());
        userToUpdate.setLogin(user.getLogin());
        userToUpdate.setPassword(user.getPassword());

        userRepository.save(userToUpdate);

        return userToUpdate;
    }

    @Transactional
    public void deleteUser(Integer id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }
}
