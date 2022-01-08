package br.com.sabrina.aws_project01.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import br.com.sabrina.aws_project01.model.ProductTest;

public interface ProductRepository extends CrudRepository<ProductTest, Long> {

    Optional<ProductTest> findByCode(String code);
    
}