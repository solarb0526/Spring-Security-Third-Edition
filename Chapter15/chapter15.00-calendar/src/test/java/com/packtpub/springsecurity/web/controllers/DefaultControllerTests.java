package com.packtpub.springsecurity.web.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Mick Knutson
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DefaultControllerTests {

    @Autowired
    private MockMvc mvc;


    @Test
    @WithUserDetails("user1@example.com")
    public void test_user1_defaultAfterLogin() throws Exception {
        mvc
                .perform(get("/default"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andDo(print())
        ;
    }

    @Test
    @WithUserDetails("admin1@example.com")
    public void test_admin1_defaultAfterLogin() throws Exception {
        mvc
                .perform(get("/default"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/events/"))
                .andDo(print())
        ;
    }


} // The End...