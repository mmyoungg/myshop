package com.myshop.web.dto;


import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MyShopResponseDtoTest {

    @Test
    public void lombokTest() {

        String name = "my";
        int amount = 100;

        MyShopResponseDto dto = new MyShopResponseDto(name, amount);

        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
