package com.leorizick.SpringWeb.repositories;

import com.leorizick.SpringWeb.domain.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categorias, Integer> {
}
