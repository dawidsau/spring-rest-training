package pl.sauermann.spring.rest.training.restwithguru.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sauermann.spring.rest.training.restwithguru.users.service.ApiService;
import pl.sauermann.spring.rest.training.restwithguru.users.user.User;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiServiceImplTest {

    @Autowired
    ApiService apiService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGetUsers() {
        List<User> users = apiService.getUsers(9);

        assertEquals(10,users.size());
    }
}