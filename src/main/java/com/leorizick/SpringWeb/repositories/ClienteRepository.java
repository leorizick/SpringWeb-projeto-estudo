package com.leorizick.SpringWeb.repositories;

import com.leorizick.SpringWeb.domain.Cliente;
import com.leorizick.SpringWeb.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
