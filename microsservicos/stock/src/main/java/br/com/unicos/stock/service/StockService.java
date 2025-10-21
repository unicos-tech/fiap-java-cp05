package br.com.unicos.stock.service;

import br.com.unicos.stock.dto.OrderRequest;
import br.com.unicos.stock.model.ProductStock;
import br.com.unicos.stock.repository.ProductStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class StockService {
    private ProductStockRepository repository;

    @Transactional
    public void apply(OrderRequest order){
        order.getItems().forEach(i -> {
            var ps = repository.findById(i.getSku()).orElse(new ProductStock(i.getSku(), 0));
            ps.setQuantity(Math.max(0, ps.getQuantity() - i.getQuantity()));
            repository.save(ps);
        });
    }
}
