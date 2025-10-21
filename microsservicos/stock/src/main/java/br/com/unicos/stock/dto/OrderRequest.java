package br.com.unicos.stock.dto;
import lombok.*; import java.util.*;

@Data @NoArgsConstructor @AllArgsConstructor
public class OrderRequest { private String orderId; private java.util.List<ProductItem> items; }
