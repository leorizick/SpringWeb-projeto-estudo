package com.leorizick.SpringWeb.repositories;

import com.leorizick.SpringWeb.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<Estado, Integer> {
}
