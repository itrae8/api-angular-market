package fr.tdetrois.formation.api_angular_market.repository;

import fr.tdetrois.formation.api_angular_market.model.CartItem;
import fr.tdetrois.formation.api_angular_market.model.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {
}
