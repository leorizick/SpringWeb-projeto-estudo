package com.leorizick.SpringWeb.repositories;

import com.leorizick.SpringWeb.domain.Categorias;
import com.leorizick.SpringWeb.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {


//    @Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.name LIKE %:name% AND cat IN :categorias")
    @Transactional
    Page<Produto> findDistinctByNameContainingAndCategoriasIn ( String name, List<Categorias> categorias, Pageable PageRequest);
}
