package com.example.tenpo.persistence.repository;

import com.example.tenpo.persistence.models.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {
    Auth findByUserIdAndExpirationDateAfterAndActiveIsTrue(Long userId, OffsetDateTime now);

    Auth findByTokenAndExpirationDateAfterAndActiveIsTrue(String token, OffsetDateTime now);
}
