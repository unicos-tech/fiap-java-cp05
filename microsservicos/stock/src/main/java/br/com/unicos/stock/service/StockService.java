package br.com.unicos.estoque.service;

import br.com.unicos.estoque.dto.OrderRequest;
import br.com.unicos.estoque.model.ProductStock;
import br.com.unicos.estoque.repo.ProductStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class StockService {
    private final ProductStockRepository repo;

    @Transactional
    public void apply(OrderRequest order){
        order.getItems().forEach(i -> {
            var ps = repo.findById(i.getSku()).orElse(new ProductStock(i.getSku(), 0));
            ps.setQuantity(Math.max(0, ps.getQuantity() - i.getQuantity()));
            repo.save(ps);
        });
    }
}
