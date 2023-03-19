package com.myshop.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MyShopResponseDto {

    private final String name;
    private final int amount;
}
