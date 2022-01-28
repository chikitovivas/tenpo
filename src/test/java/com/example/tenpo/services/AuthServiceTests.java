package com.example.tenpo.services;

import com.example.tenpo.exceptions.UnauthorizedException;
import com.example.tenpo.persistence.models.User;
import com.example.tenpo.persistence.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthServiceTests {

    @Autowired
    private AuthService authService;

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
    public void testLoginSuccessfully() {
        //make
        String token = authService.login(USERNAME, PASSWORD);

        //then
        assertNotNull(token);
    }

    @Test
    public void testLoginSuccessfullyWithAlreadyCreatedToken() {
        //make
        String token1 = authService.login(USERNAME, PASSWORD);
        String token2 = authService.login(USERNAME, PASSWORD);

        //then
        assertEquals(token1, token2);
    }

    @Test
    public void testLoginThrowsUnauthorizedException() {
        //make //then
        assertThrows(UnauthorizedException.class,
                () -> authService.login("invalid_username", PASSWORD)
        );
    }

    @Test
    public void testIsValidTokenSuccessfully() {
        //when
        String token = authService.login(USERNAME, PASSWORD);

        //make
        boolean isValid = authService.isValidToken(token);

        //then
        assertTrue(isValid);
    }

    @Test
    public void testIsValidTokenSuccessfullyWithNotValidToken() {
        //when
        String token = "not_valid_token";

        //make
        boolean isValid = authService.isValidToken(token);

        //then
        assertFalse(isValid);
    }

    @Test
    public void testLogoutSuccessfully() {
        //when
        String token1 = authService.login(USERNAME, PASSWORD);

        //make
        authService.logout(token1);

        String token2 = authService.login(USERNAME, PASSWORD);

        //then
        assertNotEquals(token1, token2);
    }

    @Test
    public void testLogoutThrowsUnauthorizedException() {
        //when
        String token = authService.login(USERNAME, PASSWORD);

        //make
        authService.logout(token);

        assertThrows(UnauthorizedException.class,
                () -> authService.logout(token)
        );
    }

    private void loadTestContext() {
        User user = new User(USERNAME, PASSWORD);
        userRepository.save(user);
    }

    private void cleanTestContext() {
        userRepository.deleteAll();
    }
}
