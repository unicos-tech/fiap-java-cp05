package br.com.unicos.checkout.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class OrderRequest {
    @NotBlank private String orderId;
    @NotEmpty private List<ProductItem> items;
}
