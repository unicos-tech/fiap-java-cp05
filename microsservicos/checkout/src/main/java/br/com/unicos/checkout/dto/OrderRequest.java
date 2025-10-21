package br.com.unicos.checkout.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.util.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class OrderRequest {
    @NotBlank private String orderId;
    @NotEmpty private List<br.com.unicos.checkout.dto.ProductItem> items;
    @Pattern(regexp="^\\+?\\d{10,15}$", message="Informe 10–15 dígitos, opcionalmente com +")
    private String phoneNumber;
}
