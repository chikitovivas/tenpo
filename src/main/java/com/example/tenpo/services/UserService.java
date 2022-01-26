package com.example.tenpo.services;

import com.example.tenpo.exceptions.BadRequestException;
import com.example.tenpo.exceptions.ConflictException;
import com.example.tenpo.persistence.models.User;
import com.example.tenpo.persistence.repository.UserRepository;
import com.example.tenpo.utils.HashUtils;
import com.example.tenpo.utils.StringUtils;
import com.google.common.base.Strings;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signUp(String username, String password) throws ServiceException {
        String normalizedUsername = StringUtils.normalizeString(username);
        String normalizedPassword = StringUtils.normalizeString(password);

        boolean isValidData = validateUsernameAndPassword(normalizedUsername, normalizedPassword);
        if(!isValidData) {
            throw new BadRequestException("Username or Password cannot be null or empty");
        }

        String hashedPassword = HashUtils.hash(normalizedPassword);

        User user = new User()
                .setUsername(normalizedUsername)
                .setPassword(hashedPassword);

        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw handleSaveExceptions(e, username);
        }
    }

    private boolean validateUsernameAndPassword(String username, String password) {
        return !Strings.isNullOrEmpty(username) && !Strings.isNullOrEmpty(password);
    }

    private RuntimeException handleSaveExceptions(Exception e, String username) {
        if (e.getMessage().contains("users_username_uindex")) {
            return new ConflictException(
                    String.format("Username: '%s' is already taken!", username)
            );
        } else {
            return new ServiceException(
                    String.format("Error: %s", e.getMessage())
            );
        }
    }

    public User getByUsernameAndPassword(String username, String password) {
        String normalizedUsername = StringUtils.normalizeString(username);
        String normalizedPassword = StringUtils.normalizeString(password);
        return findUser(normalizedUsername, normalizedPassword);
    }

    private User findUser(String normalizedUsername, String normalizedPassword) {
        //Check first if password already is hashed
        User user = userRepository.findByUsernameAndPassword(normalizedUsername, normalizedPassword);
        if (Objects.isNull(user)) {
            String hashedPassword = HashUtils.hash(normalizedPassword);
            user = userRepository.findByUsernameAndPassword(normalizedUsername, hashedPassword);
        }
        return user;
    }
}
