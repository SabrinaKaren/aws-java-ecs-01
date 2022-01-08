package br.com.sabrina.aws_project01.controller;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sabrina.aws_project01.model.Product;
import br.com.sabrina.aws_project01.repository.ProductRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductRepository productRepository;
    
    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable long id) {
        Optional<Product> productOp = productRepository.findById(id);
        if (productOp.isPresent()) {
            return new ResponseEntity<Product>(productOp.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product productCreated = productRepository.save(product);
        return new ResponseEntity<Product>(productCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            Product productUpdated = productRepository.save(product);
            return new ResponseEntity<Product>(productUpdated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable long id) {
        Optional<Product> productOp = productRepository.findById(id);
        if (productOp.isPresent()) {
            productRepository.delete(productOp.get());
            return new ResponseEntity<Product>(productOp.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findByCode(@PathVariable String code) {
        Optional<Product> productOp = productRepository.findByCode(code);
        if (productOp.isPresent()) {
            return new ResponseEntity<Product>(productOp.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
}