package com.myshop.web;

import com.myshop.web.dto.MyShopResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyShopController {

    @GetMapping("/shop")
    public String myShop() {
        return "mySjhop";
    }

    @GetMapping("/shop/dto")
    public MyShopResponseDto myShopDto(@RequestParam("name") String name,
                                       @RequestParam("amount") int amount) {
        return new MyShopResponseDto(name, amount);
    }

}
