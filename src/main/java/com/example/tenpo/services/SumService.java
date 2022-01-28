package com.example.tenpo.services;

import com.example.tenpo.exceptions.UnauthorizedException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

@Service
public class SumService {

    private AuthService authService;

    public SumService(AuthService authService) {
        this.authService = authService;
    }

    public Integer makeSum(String token, Integer sum1, Integer sum2) throws ServiceException {

        if (!authService.isValidToken(token)) {
            throw new UnauthorizedException("Invalid token");
        }

        return Integer.sum(sum1, sum2);
    }
}
