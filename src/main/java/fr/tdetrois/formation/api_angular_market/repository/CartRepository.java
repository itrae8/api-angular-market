package fr.tdetrois.formation.api_angular_market.repository;

import fr.tdetrois.formation.api_angular_market.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    Optional<Cart> findByUserId(Integer userId);
}
