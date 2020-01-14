package com.packtpub.springsecurity.web.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Mick Knutson
 */
@DirtiesContext
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

//@WebMvcTest(DefaultController.class)
public class DefaultControllerTests {

    @Autowired
    MockMvc mvc;

//    @MockBean
//    CreateClientService createClientServiceMock;
//
//    @Autowired
//    ObjectMapper objectMapper;

    @Test
    public void noop() throws Exception {
    }

    /*
//    @Test
//    public void testCreateClientSuccessfully() throws Exception {
//        given(createClientServiceMock.createClient("Foo")).willReturn(new Client("Foo"));
//
//        mockMvc.perform(post("/clients")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsBytes(new CreateClientRequest("Foo"))))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.name", is("Foo")))
//                .andExpect(jsonPath("$.number", notNullValue()));
//    }

    @Test
    @WithUserDetails("user1@example.com")
    public void test_user1_defaultAfterLogin() throws Exception {
        mockMvc
                .perform(get("/default"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andDo(print())
        ;
    }

    @Test
    @WithUserDetails("admin1@example.com")
    public void test_admin1_defaultAfterLogin() throws Exception {
        mockMvc
                .perform(get("/default"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/events/"))
                .andDo(print())
        ;
    }




    @Test
    public void test_rootContext() throws Exception {

        OAuth2RestTemplate template = template();

        String url = baseUrl+"/";
        String expected = "{'message': 'welcome to the JBCP Calendar Application'}";

        logger.info(" CALLING: {}", url);
        String result = template.getForObject(url, String.class);
        logger.info(" RESULT: {}", result);

        assertThat(result, is(expected));

    }

    @Test
    public void test_api() throws Exception {

        OAuth2RestTemplate template = template();

        String url = baseUrl+"/api";
        String expected = "{'message': 'welcome to the JBCP Calendar Application API'}";

        logger.info(" CALLING: {}", url);
        String result = template.getForObject(url, String.class);
        logger.info(" RESULT: {}", result);

        assertThat(result, is(expected));

    }

//    @Test
    public void test_signup() throws Exception {

        OAuth2RestTemplate template = template();

        String url = baseUrl+"/signup/new";
        String expected = "foobar";

        logger.info(" CALLING: {}", url);
        String result = template.getForObject(url, String.class);
        logger.info(" RESULT: {}", result);

        assertThat(result, is(expected));

    }

//    @Test
    public void test_post_signup() throws Exception {

        OAuth2RestTemplate template = template();

        String url = baseUrl+"/signup/new";
        String expected = "foobar";

        logger.info(" CALLING: {}", url);
        String result = template.getForObject(url, String.class);

//        String result = template.postForObject(url, Object request, String.class, Object... uriVariables)

//        String result = template.postForObject(url, String.class);
        logger.info(" RESULT: {}", result);

        assertThat(result, is(expected));

    }

    /*@Test
    @WithUserDetails("user1@example.com")
    public void test_events_user() throws Exception {
        mockMvc
                .perform(get("/admin/h2")
                        .header("X-Requested-With", "XMLHttpRequest")
                )
                .andExpect(status().isUnauthorized());

        OAuth2RestTemplate template = template();

        String url = baseUrl+"/events";
        String expected = "foobar";

        logger.info(" CALLING: {}", url);
        String result = template.getForObject(url, String.class);
        logger.info(" RESULT: {}", result);

        assertThat(result, is(expected));

    }


    @Test
    @WithUserDetails("admin1@example.com")
    public void test_events_admin() throws Exception {

        OAuth2RestTemplate template = template();

        String url = baseUrl+"/events";
        String expected = "foobar";

        logger.info(" CALLING: {}", url);
        String result = template.getForObject(url, String.class);
        logger.info(" RESULT: {}", result);

        assertThat(result, is(expected));

    }*/

    /*
    //    @Test
    public void test_user1_Events() throws Exception {

        OAuth2RestTemplate template = template();

        String url = baseUrl+"/events/my/0";
        String expected = "foobar";

        logger.info(" CALLING: {}", url);
        String result = template.getForObject(url, String.class);
        logger.info(" RESULT: {}", result);

        assertThat(result, is(expected));

    }

    @Test
    public void test_myEvents() throws Exception {

        OAuth2RestTemplate template = template();

        String url = baseUrl+"/events/my";
        String expected = "{\"currentUser\":[{\"id\":100,\"summary\":\"Birthday Party\",\"description\":\"This is going to be a great birthday\",\"when\":1499135400000}]}";

        logger.info(" CALLING: {}", url);
        String result = template.getForObject(url, String.class);
        logger.info(" RESULT: {}", result);

        assertThat(result, is(expected));

    }

    @Test
    public void test_show() throws Exception {

        OAuth2RestTemplate template = template();

        String url = baseUrl+"/events/101";
        String expected = "{\"id\":101,\"summary\":\"Conference Call\",\"description\":\"Call with the client\",\"when\":1514059200000}";

        logger.info(" CALLING: {}", url);
        String result = template.getForObject(url, String.class);
        logger.info(" RESULT: {}", result);

        assertThat(result, is(expected));

    }

    //    @Test
    public void test_new_create_new_EventFormAutoPopulate() throws Exception {

        OAuth2RestTemplate template = template();

        String url = baseUrl+"/events/new?auto";
        String expected = "foobar";

        logger.info(" CALLING: {}", url);
        String result = template.getForObject(url, String.class);
//        String result = template.postForObject(url, String.class);
        logger.info(" RESULT: {}", result);

        assertThat(result, is(expected));

    }

    //    @Test
    public void test_createEvent() throws Exception {

        OAuth2RestTemplate template = template();

        String url = baseUrl+"/events/new";
        String expected = "foobar";

        logger.info(" CALLING: {}", url);
        String result = template.getForObject(url, String.class);
        logger.info(" RESULT: {}", result);

        assertThat(result, is(expected));

    }

    @Test
    public void test_default() throws Exception {

        OAuth2RestTemplate template = template();

        String url = baseUrl+"/default";
        String expected = "{'message': 'welcome to the JBCP Calendar Application'}";

        logger.info(" CALLING: {}", url);
        String result = template.getForObject(url, String.class);
        logger.info(" RESULT: {}", result);

        assertThat(result, is(expected));

    }



    */


} // The End...