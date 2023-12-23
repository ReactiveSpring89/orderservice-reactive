package com.app.orderservice.serviceclients;

import com.app.orderservice.models.dto.ProductDTO;
import com.app.orderservice.models.dto.TransactionRequestDTO;
import com.app.orderservice.models.dto.TransactionResponseDTO;
import com.app.orderservice.models.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserClient {
    private final WebClient webClient;

    public UserClient(@Value("${user.service.url}") String url) {
        this.webClient = WebClient.builder()
                .baseUrl(url).build();
    }

    public Mono<TransactionResponseDTO> authorizeTransaction(TransactionRequestDTO transactionRequestDTO) {
        return webClient.post()
                .uri("transaction")
                .bodyValue(transactionRequestDTO)
                .retrieve()
                .bodyToMono(TransactionResponseDTO.class);
    }

    public Flux<UserDTO> getAllUsers() {
        return webClient.get()
                .retrieve()
                .bodyToFlux(UserDTO.class);
    }
}
