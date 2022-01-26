package com.example.tenpo.services;

import com.example.tenpo.exceptions.UnauthorizedException;
import com.example.tenpo.persistence.models.Auth;
import com.example.tenpo.persistence.models.User;
import com.example.tenpo.persistence.repository.AuthRepository;
import com.example.tenpo.utils.HashUtils;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Objects;

@Service
public class AuthService {
    private UserService userService;
    private AuthRepository authRepository;

    private Integer TOKEN_TTL = 1;

    public AuthService(UserService userService, AuthRepository authRepository) {
        this.userService = userService;
        this.authRepository = authRepository;
    }

    public String login(String username, String password) throws ServiceException {
        User user = userService.getByUsernameAndPassword(username, password);
        if (Objects.isNull(user)) {
            throw new UnauthorizedException("Invalid Credentials");
        }
        Auth auth = authRepository.findByUserIdAndExpirationDateAfterAndActiveIsTrue(user.getId(), OffsetDateTime.now());
        if (Objects.nonNull(auth)) {
            return auth.getToken();
        }

        return authRepository.save(createAuth(user)).getToken();
    }

    private Auth createAuth(User user) {
        OffsetDateTime expirationDate = OffsetDateTime.now().plus(Duration.ofMinutes(TOKEN_TTL));
        String token = buildToken(user, expirationDate);
        return new Auth(user.getId(), token, expirationDate);
    }

    private String buildToken(User user, OffsetDateTime expirationDate) {
        String baseToken = String.format("%s.%s", user.getId(), expirationDate);
        return HashUtils.hash(baseToken);
    }

    public boolean isValidToken(String token) {
        Auth auth = authRepository.findByTokenAndExpirationDateAfterAndActiveIsTrue(token, OffsetDateTime.now());
        return Objects.nonNull(auth);
    }

    public void logout(String token) {
        Auth auth = authRepository.findByTokenAndExpirationDateAfterAndActiveIsTrue(token, OffsetDateTime.now());
        if (Objects.isNull(auth)) {
            throw new UnauthorizedException("Invalid token");
        }
        auth.setActive(false);
        authRepository.save(auth);
    }
}
