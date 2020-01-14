package com.packtpub.springsecurity.repository;

import com.packtpub.springsecurity.domain.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class RoleRepositoryTests {

    @Autowired
    private RoleRepository repository;

    @Test
    public void validateUser_User() {
        Role user = repository.findOne(0);
        assertThat(user.getId()).isEqualTo(0);
        assertThat(user.getName()).isEqualTo("ROLE_USER");
        assertThat(user.getUsers().size()).isEqualTo(3);
    }

    @Test
    public void validateUser_Admin() {
        Role user = repository.findOne(1);
        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.getName()).isEqualTo("ROLE_ADMIN");
        assertThat(user.getUsers().size()).isEqualTo(1);
    }

} // The End...
