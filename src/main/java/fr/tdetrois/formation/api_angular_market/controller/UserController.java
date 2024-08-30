package fr.tdetrois.formation.api_angular_market.controller;

import fr.tdetrois.formation.api_angular_market.model.User;
import fr.tdetrois.formation.api_angular_market.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.addUser(user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> modifyUser(@PathVariable("id") Integer id, @RequestBody User User) {

        User UserUpdated;
        try {
            UserUpdated = userService.modifyUser(id, User);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(UserUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {

        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
