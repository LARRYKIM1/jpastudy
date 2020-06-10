package com.larrykim.jpastudy;

import com.larrykim.jpastudy.web.ItemController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {ItemController.class})
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void 아이템_컨트롤러_테스트() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/item/new"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
