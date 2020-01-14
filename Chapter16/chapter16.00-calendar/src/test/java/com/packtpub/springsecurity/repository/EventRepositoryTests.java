package com.packtpub.springsecurity.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest//(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DataJpaTest
@EnableJpaAuditing
public class EventRepositoryTests {

    @Autowired
    private EventRepository repository;

    @Test
    public void validateUser_Event() {
        int userId = 0;
//        List<Event> events = repository.findForUser(userId);
//        assertThat(events.get(0).getSummary()).isEqualTo("Birthday Party");
    }

}
