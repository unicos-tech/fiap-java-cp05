package br.com.unicos.estoque.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product_stock")
@Data @NoArgsConstructor @AllArgsConstructor
public class ProductStock {
    @Id private String sku;
    private int quantity;
}
