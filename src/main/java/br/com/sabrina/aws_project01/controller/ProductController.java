package br.com.sabrina.aws_project01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.sabrina.aws_project01.enums.EventType;
import br.com.sabrina.aws_project01.model.ProductTest;
import br.com.sabrina.aws_project01.repository.ProductRepository;
import br.com.sabrina.aws_project01.service.ProductPublisher;

import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductRepository productRepository;
    private ProductPublisher productPublisher;

    @Autowired
    public ProductController(ProductRepository productRepository, ProductPublisher productPublisher) {
        this.productRepository = productRepository;
        this.productPublisher = productPublisher;
    }

    @GetMapping
    public Iterable<ProductTest> findAll() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductTest> findById(@PathVariable long id) {
        Optional<ProductTest> optProduct = productRepository.findById(id);
        if (optProduct.isPresent()) {
            return new ResponseEntity<ProductTest>(optProduct.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ProductTest> saveProduct(@RequestBody ProductTest ProductTest) {
        ProductTest productCreated = productRepository.save(ProductTest);
        productPublisher.publishProductEvent(productCreated, EventType.PRODUCT_CREATED, "jojoh");
        return new ResponseEntity<ProductTest>(productCreated, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProductTest> updateProduct(@RequestBody ProductTest product, @PathVariable("id") long id) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            ProductTest productUpdated = productRepository.save(product);
            productPublisher.publishProductEvent(productUpdated, EventType.PRODUCT_UPDATED, "orlando");
            return new ResponseEntity<ProductTest>(productUpdated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ProductTest> deleteProduct(@PathVariable("id") long id) {
        Optional<ProductTest> optProduct = productRepository.findById(id);
        if (optProduct.isPresent()) {
            ProductTest product = optProduct.get();
            productRepository.delete(product);
            productPublisher.publishProductEvent(product, EventType.PRODUCT_DELETED, "tampa");
            return new ResponseEntity<ProductTest>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/bycode")
    public ResponseEntity<ProductTest> findByCode(@RequestParam String code) {
        Optional<ProductTest> optProduct = productRepository.findByCode(code);
        if (optProduct.isPresent()) {
            return new ResponseEntity<ProductTest>(optProduct.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}