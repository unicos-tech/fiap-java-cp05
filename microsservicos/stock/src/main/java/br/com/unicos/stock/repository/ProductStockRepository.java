package br.com.unicos.estoque.repo;

import br.com.unicos.estoque.model.ProductStock;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductStockRepository extends MongoRepository<ProductStock, String> {}
