package com.packtpub.springsecurity.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
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
