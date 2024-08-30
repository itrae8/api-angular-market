package fr.tdetrois.formation.api_angular_market.controller;

import fr.tdetrois.formation.api_angular_market.model.Cart;
import fr.tdetrois.formation.api_angular_market.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable("userId") Integer userId) {

        Cart result = cartService.getCartByUserId(userId);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/user/{userId}/product/{productId}/quantity/{quantity}")
    public ResponseEntity<Cart> createCart(
            @PathVariable("userId") Integer userId,
            @PathVariable("productId") Integer productId,
            @PathVariable("quantity") Integer quantity
    ) {
        return ResponseEntity.ok().body(cartService.createCart(userId, productId, quantity));
    }

    @PatchMapping("/{cartId}/product/{productId}/quantity/{quantity}")
    public ResponseEntity<Cart> updateCart(
            @PathVariable("cartId") Integer cartId,
            @PathVariable("productId") Integer productId,
            @PathVariable("quantity") Integer quantity
    ){
        return ResponseEntity.ok().body(cartService.updateCart(cartId, productId, quantity));
    }

}
