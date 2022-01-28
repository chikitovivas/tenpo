package com.example.tenpo.services;

import com.example.tenpo.persistence.models.Log;
import com.example.tenpo.persistence.models.User;
import com.example.tenpo.persistence.repository.LogRepository;
import com.example.tenpo.persistence.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LogServiceTests {

    @Autowired
    private AuthService authService;

    @Autowired
    private LogService logService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LogRepository logRepository;

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
    public void testLogSuccessfully() {
        //when
        String url = "url";
        User userRequest = new User(USERNAME, PASSWORD);
        User userResponse = new User();

        //make
        logService.log(url, userRequest, userResponse);

        Pageable pageable = PageRequest.of(0,10);
        Page<Log> page = logRepository.findAll(pageable);

        //then
        assertEquals(1, page.getTotalElements());
    }

    @Test
    public void testGetAllSuccessfully() {
        //when
        String token = authService.login(USERNAME, PASSWORD);
        Integer index = 0;
        Integer pageSize = 10;

        //make
        Page<Log> page = logService.getAll(token, index, pageSize);

        //then
        assertEquals(0, page.getTotalElements());
    }

    @Test
    public void testGetAllSuccessfullyWith10Logs() {
        //when
        String token = authService.login(USERNAME, PASSWORD);
        Integer index = 0;
        Integer pageSize = 10;
        saveLogs(20);

        //make
        Page<Log> page = logService.getAll(token, index, pageSize);

        //then
        assertEquals(10, page.getNumberOfElements());
        assertEquals(20, page.getTotalElements());
        assertEquals(2, page.getTotalPages());
    }

    private void saveLogs(int size) {
        String url = "url";
        User userRequest = new User(USERNAME, PASSWORD);
        User userResponse = new User();

        for (int i = 0; i < size; i++) {
            logService.log(url, userRequest, userResponse);
        }
    }

    private void loadTestContext() {
        User user = new User(USERNAME, PASSWORD);
        userRepository.save(user);
    }

    private void cleanTestContext() {
        userRepository.deleteAll();
        logRepository.deleteAll();
    }
}
