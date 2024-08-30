package fr.tdetrois.formation.api_angular_market.repository;

import fr.tdetrois.formation.api_angular_market.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository  extends JpaRepository<Product, Integer> {
}
