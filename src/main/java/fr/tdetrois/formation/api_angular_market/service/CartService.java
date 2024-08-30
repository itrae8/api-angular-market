package fr.tdetrois.formation.api_angular_market.service;

import fr.tdetrois.formation.api_angular_market.model.Cart;
import fr.tdetrois.formation.api_angular_market.model.CartItem;
import fr.tdetrois.formation.api_angular_market.model.Product;
import fr.tdetrois.formation.api_angular_market.model.User;
import fr.tdetrois.formation.api_angular_market.repository.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    private final UserService userService;

    private final ProductService productService;

    public Cart getCartById(Integer id) {
        return cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found with id: " + id));
    }

    public Cart getCartByUserId(Integer id) {
        Optional<Cart> optCart = cartRepository.findByUserId(id);
        return optCart.orElse(null);
    }

    @Transactional
    public Cart createCart(Integer userId, Integer productId, Integer quantity) {

        if (quantity <= 0) {
            throw new RuntimeException("The quantity must be at least 1");
        }

        User user = userService.getUserById(userId);
        Product product = productService.getProductById(productId);
        Cart existingCart = getCartByUserId(userId);

        if (existingCart != null) {
            throw new RuntimeException(String.format("The user %s %s already has a cart",
                    existingCart.getUser().getFirstname(),
                    existingCart.getUser().getLastname()));
        }

        Cart cartToCreate = new Cart();
        cartToCreate.setUser(user);

        CartItem cartItem = new CartItem();
        cartItem.setCart(cartToCreate);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        cartToCreate.setCartItems(Collections.singletonList(cartItem));

        return cartRepository.save(cartToCreate);
    }

    @Transactional
    public Cart updateCart(Integer cartId, Integer productId, Integer quantity) {

        Cart cart = getCartById(cartId);

        Map<Integer, CartItem> existingItemsMap = cart.getCartItems()
                .stream()
                .collect(Collectors.toMap(ci -> ci.getProduct().getId(), ci -> ci));

        CartItem existingItem = existingItemsMap.get(productId);

        if (existingItem != null) {
            if (quantity > 0) {
                existingItem.setQuantity(quantity);
            } else {
                cart.getCartItems().remove(existingItem);
            }
        } else if (quantity > 0) {
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setQuantity(quantity);
            cartItem.setProduct(productService.getProductById(productId));

            cart.getCartItems().add(cartItem);
        }

        cartRepository.save(cart);

        if (cart.getCartItems().isEmpty()) {
            cartRepository.delete(cart);
            return null;
        }

        return cart;
    }
}
