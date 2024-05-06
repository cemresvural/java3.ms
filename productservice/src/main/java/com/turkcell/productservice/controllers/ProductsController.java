package com.turkcell.productservice.controllers;


import com.turkcell.core3.TestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {


    @GetMapping
    public int getStock(@RequestParam int productId){
        return 5;
    }


    @GetMapping("{id}")
    public int test(@PathVariable int id){
        return id;
    }


    @GetMapping("test")
    public String get(){
        return TestService.hello();
    }
}
