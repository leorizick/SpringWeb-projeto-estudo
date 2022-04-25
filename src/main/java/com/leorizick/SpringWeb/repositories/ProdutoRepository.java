package com.leorizick.SpringWeb.repositories;

import com.leorizick.SpringWeb.domain.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produtos, Integer> {
}
