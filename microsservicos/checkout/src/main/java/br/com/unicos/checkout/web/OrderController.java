package br.com.unicos.checkout.web;

import br.com.unicos.checkout.dto.OrderRequest;
import br.com.unicos.checkout.service.OrderPublisher;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private OrderPublisher publisher;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody OrderRequest req){
        publisher.publish(req);
        return ResponseEntity.accepted().build();
    }
}
