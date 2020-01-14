package com.packtpub.springsecurity.web.controllers;

import com.packtpub.springsecurity.CalendarApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * https://dzone.com/articles/testing-improvements-in-spring-boot-14
 *
 * @author Mick Knutson
 */
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CalendarApplication.class)
@AutoConfigureMockMvc//(secure=false)
//@Transactional
public class SignupControllerTests {

    @Autowired
    MockMvc mockMvc;

    /*
    @Before
        public void beforeEachTest() {
         mockMvc = MockMvcBuilders.standaloneSetup(new IndexController()).build();
    }
     */

    @Test
    public void noop() throws Exception {
    }


    /*@Test
    public void test_signup_form() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/signup/form"))
                .andExpect(status().isOk())
                .andExpect(view().name("signup/form"))
//                .andExpect(content().contentType("text/html; charset=UTF-8"))
                .andExpect(model().attributeExists("signupForm"))
                .andExpect(content().string(containsString("Calendar User Signup")))
                .andDo(print())
        ;
    }

    @Test
    public void test_POST_signup_new() throws Exception {
        mockMvc
                .perform(newUserToPost())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(flash().attribute("message", "You have successfully signed up and logged in."))

                .andDo(print())
        ;
    }

    @Test
    public void test_POST_signup_existing() throws Exception {
        mockMvc
                .perform(existingUserToPost())
                .andExpect(status().isOk())
                .andExpect(view().name("signup/form"))
//                .andExpect(content().contentType("text/html; charset=UTF-8"))
                .andExpect(model().attributeHasFieldErrors("signupForm"))
                .andExpect(model().hasErrors())
                .andDo(print())
        ;
    }*/


    private RequestBuilder newUserToPost() {
        RequestBuilder req = post("/signup/new")
                .param("email", "bob1@example.com")
                .param("firstName", "Bob")
                .param("lastName", "One")
                .param("password", "bob1")
                .accept(MediaType.TEXT_HTML)
//                .with(csrf())
                ;

        return req;
    }

    private RequestBuilder existingUserToPost() {
        RequestBuilder req = post("/signup/new")
                .param("email", "user1@example.com")
                .param("firstName", "User")
                .param("lastName", "One")
                .param("password", "user")
                .accept(MediaType.TEXT_HTML)
//                .with(csrf())
                ;

        return req;
    }


} // The End...