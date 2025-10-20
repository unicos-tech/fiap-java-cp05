package br.com.unicos.estoque.dto;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProductItem { private String sku; private int quantity; }
