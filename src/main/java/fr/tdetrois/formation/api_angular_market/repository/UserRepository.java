package fr.tdetrois.formation.api_angular_market.repository;

import fr.tdetrois.formation.api_angular_market.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
