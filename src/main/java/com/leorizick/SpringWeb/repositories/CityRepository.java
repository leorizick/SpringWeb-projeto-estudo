package com.leorizick.SpringWeb.repositories;

import com.leorizick.SpringWeb.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<Cidade, Integer> {
}
