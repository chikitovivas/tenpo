package com.example.tenpo.services;

import com.example.tenpo.exceptions.UnauthorizedException;
import com.example.tenpo.persistence.models.User;
import com.example.tenpo.persistence.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SumServiceTests {

    @Autowired
    private SumService sumService;

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
    public void testMakeSumSuccessfully() {
        //when
        Integer num1 = 1;
        Integer num2 = 2;
        String token = authService.login(USERNAME, PASSWORD);

        //make
        Integer sum = sumService.makeSum(token, num1, num2);

        //then
        assertEquals(num1 + num2, sum);
    }

    @Test
    public void testMakeSumThrowsWithInvalidToken() {
        //when
        String token = "invalid_token";
        Integer num1 = 1;
        Integer num2 = 2;

        //make
        assertThrows(UnauthorizedException.class,
                () -> sumService.makeSum(token, num1, num2)
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
