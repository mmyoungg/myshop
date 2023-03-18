package com.myshop.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = MyShopController.class)
public class MyShopControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void myShopReturnTest() throws Exception {
        String myShop = "myShop";

        mvc.perform(get("/shop"))
                .andExpect(status().isOk())
                .andExpect(content().string(myShop));
    }
}
