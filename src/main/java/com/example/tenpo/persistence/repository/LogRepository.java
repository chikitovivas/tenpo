package com.example.tenpo.persistence.repository;

import com.example.tenpo.persistence.models.Log;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends PagingAndSortingRepository<Log, Long> {
}
