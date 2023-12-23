package com.app.orderservice;

import com.app.orderservice.models.dto.ProductDTO;
import com.app.orderservice.models.dto.PurchaseOrderRequestDTO;
import com.app.orderservice.models.dto.PurchaseOrderResponseDTO;
import com.app.orderservice.models.dto.UserDTO;
import com.app.orderservice.service.OrderFulfillmentService;
import com.app.orderservice.serviceclients.ProductClient;
import com.app.orderservice.serviceclients.UserClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class OrderserviceReactiveApplicationTests {
	@Autowired
	private UserClient userClient;

	@Autowired
	private ProductClient productClient;

	@Autowired
	private OrderFulfillmentService orderFulfillmentService;

	@Test
	void contextLoads() {
		Flux<PurchaseOrderResponseDTO> dtoFlux = Flux.zip(userClient.getAllUsers(), productClient.getAllProducts())
				.map(t -> buildDTO(t.getT1(), t.getT2()))
				.flatMap(dto -> orderFulfillmentService.processOrder(Mono.just(dto)))
				.doOnNext(System.out::println);

		StepVerifier.create(dtoFlux)
				.expectNextCount(0)
				.verifyComplete();
	}

	private PurchaseOrderRequestDTO buildDTO(UserDTO userDTO, ProductDTO productDTO) {
		PurchaseOrderRequestDTO purchaseOrderRequestDTO = new PurchaseOrderRequestDTO();
		purchaseOrderRequestDTO.setUserId(userDTO.getId());
		purchaseOrderRequestDTO.setProductId(productDTO.getId());
		return purchaseOrderRequestDTO;
	}

}
