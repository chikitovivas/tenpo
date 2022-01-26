package com.example.tenpo.persistence.repository;

import com.example.tenpo.persistence.models.Auth;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public interface AuthRepository extends CrudRepository<Auth, Long> {
    Auth findByUserIdAndExpirationDateAfterAndActiveIsTrue(Long userId, OffsetDateTime now);

    Auth findByTokenAndExpirationDateAfterAndActiveIsTrue(String token, OffsetDateTime now);
}
