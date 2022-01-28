package com.example.tenpo.services;

import com.example.tenpo.exceptions.BadRequestException;
import com.example.tenpo.exceptions.ConflictException;
import com.example.tenpo.persistence.models.User;
import com.example.tenpo.persistence.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private static final String USERNAME = "chikitovivas";
    private static final String PASSWORD = "123456";

    @BeforeEach
    public void setUp() {
        loadTestContext();
    }

    @AfterEach
    public void cleanUp() {
        cleanTestContext();
    }

    @Test
    public void testSignUpSuccessfully() {
        //when
        String username = "chikito";
        String password = "123456";

        //make
        User user = userService.signUp(username, password);

        //then
        assertNotNull(user);
        assertEquals(username, user.getUsername());
    }

    @Test
    public void testSignUpThrowsBadRequestException() {
        //when
        String username = "";
        String password = "123456";

        //make
        assertThrows(BadRequestException.class,
                () -> userService.signUp(username, password)
        );
    }

    @Test
    public void testSignUpThrowsConflictException() {
        //make
        assertThrows(ConflictException.class,
                () -> userService.signUp(USERNAME, PASSWORD)
        );
    }

    @Test
    public void testGetByUsernameAndPasswordSuccessfully() {
        //make
        User user = userService.getByUsernameAndPassword(USERNAME, PASSWORD);

        //then
        assertNotNull(user);
        assertEquals(USERNAME, user.getUsername());
    }

    @Test
    public void testGetByUsernameAndPasswordSuccessfullyNotFound() {
        //make
        User user = userService.getByUsernameAndPassword("not_found_username", PASSWORD);

        //then
        assertNull(user);
    }

    private void loadTestContext() {
        User user = new User(USERNAME, PASSWORD);
        userRepository.save(user);
    }

    private void cleanTestContext() {
        userRepository.deleteAll();
    }
}
