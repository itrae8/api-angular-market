package fr.tdetrois.formation.api_angular_market.service;

import fr.tdetrois.formation.api_angular_market.model.Product;
import fr.tdetrois.formation.api_angular_market.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public Product addProduct(Product product) {
        Product productToSave = new Product();

        productToSave.setName(product.getName());
        productToSave.setPrice(product.getPrice());
        productToSave.setDescription(product.getDescription());
        productToSave.setImageLink(product.getImageLink());

        productRepository.save(productToSave);
        return productToSave;
    }

    @Transactional
    public Product modifyProduct(Integer id, Product product) {
        Optional<Product> optProduct = productRepository.findById(id);
        if (optProduct.isEmpty()) {
            throw new RuntimeException("Product not found");
        }
        Product productToUpdate = optProduct.get();

        productToUpdate.setName(product.getName());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setImageLink(product.getImageLink());

        productRepository.save(productToUpdate);

        return productToUpdate;
    }

    @Transactional
    public void deleteProduct(Integer id) {
        if (productRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }
}
