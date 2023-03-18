package com.myshop.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyShopController {

    @GetMapping("/shop")
    public String myShop() {
        return "mySjhop";
    }
}
