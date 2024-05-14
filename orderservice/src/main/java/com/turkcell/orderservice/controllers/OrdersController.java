package com.turkcell.orderservice.controllers;


import com.turkcell.orderservice.clients.ProductServiceClient;
import com.turkcell.orderservice.core.configuration.events.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {


      private final ProductServiceClient productServiceClient;
      private final KafkaTemplate<String,Object> kafkaTemplate;

    @PostMapping
    public String addOrder(@RequestParam int productId) {

        //should go to the product service and get stock information of the products

       /* var result= webClientBuilder
                .build()
                .get()
                .uri("http://localhost:8086/api/v1/products?productId="+productId)
                .retrieve()
                .bodyToMono(Integer.class)
                .block();

*/

        int stockResult=productServiceClient.getStockByProductId(productId);
        System.out.println("Reply from product service: " + stockResult);


        if(stockResult <=0)
            throw new RuntimeException("The product not in stock.");



        kafkaTemplate.sendDefault("NewOrder",new OrderCreatedEvent(1, LocalDateTime.now().minusDays(3)));
        return "Order added.";
    }
}
