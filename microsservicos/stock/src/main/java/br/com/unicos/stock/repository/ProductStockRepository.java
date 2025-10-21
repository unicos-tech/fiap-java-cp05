package br.com.unicos.stock.repository;

import br.com.unicos.stock.model.ProductStock;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductStockRepository extends MongoRepository<ProductStock, String> {}
