package fr.tdetrois.formation.api_angular_market.controller;

import fr.tdetrois.formation.api_angular_market.model.Product;
import fr.tdetrois.formation.api_angular_market.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok().body(productService.addProduct(product));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> modifyProduct(@PathVariable("id") Integer id, @RequestBody Product product) {

        Product productUpdated;
        try {
            productUpdated = productService.modifyProduct(id, product);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(productUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer id) {

        try {
            productService.deleteProduct(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
