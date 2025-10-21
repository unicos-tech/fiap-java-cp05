package br.com.unicos.checkout.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProductItem {
    @NotBlank private String sku;
    @Positive private int quantity;
}
