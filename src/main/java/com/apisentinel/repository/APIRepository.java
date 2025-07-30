package com.apisentinel.repository;

import com.apisentinel.entity.API;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APIRepository extends JpaRepository<API, Long> {
}
