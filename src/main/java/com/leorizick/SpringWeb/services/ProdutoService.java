package com.leorizick.SpringWeb.services;

import com.leorizick.SpringWeb.domain.Categorias;
import com.leorizick.SpringWeb.domain.Produto;
import com.leorizick.SpringWeb.repositories.CategoriaRepository;
import com.leorizick.SpringWeb.repositories.ProdutoRepository;
import com.leorizick.SpringWeb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository repo;

    public Produto find(Integer id) {
        Optional<Produto> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
    }

    public Page<Produto> search (String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categorias> categorias = categoriaRepository.findAllById(ids);
        return repo.findDistinctByNameContainingAndCategoriasIn(nome,categorias, pageRequest);
    }

}
