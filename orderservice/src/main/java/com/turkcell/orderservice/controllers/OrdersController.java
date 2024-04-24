package com.turkcell.orderservice.controllers;


import com.turkcell.orderservice.clients.ProductServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {

   // private final WebClient.Builder webClientBuilder;
      private final ProductServiceClient productServiceClient;

    @PostMapping
    public String addOrder(@RequestParam int productId){

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
        return "Order added.";
    }
}
