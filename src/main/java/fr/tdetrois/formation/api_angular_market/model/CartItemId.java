package fr.tdetrois.formation.api_angular_market.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartItemId {
    private Integer cart;
    private Integer product;
}
