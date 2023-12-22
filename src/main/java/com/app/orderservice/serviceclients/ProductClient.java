package com.app.orderservice.serviceclients;


import com.app.orderservice.models.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ProductClient {

    private final WebClient webClient;

    public ProductClient(@Value("${product.service.url}") String url) {
        this.webClient = WebClient.builder()
                .baseUrl(url).build();
    }

    public Mono<ProductDTO> getProductById(final String id) {
        return webClient
                .get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(ProductDTO.class);

    }
}
