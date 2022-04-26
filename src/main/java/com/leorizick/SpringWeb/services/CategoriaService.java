package com.leorizick.SpringWeb.services;

import com.leorizick.SpringWeb.domain.Categorias;
import com.leorizick.SpringWeb.repositories.CategoriaRepository;
import com.leorizick.SpringWeb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categorias find(Integer id) {
        Optional<Categorias> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categorias.class.getName()));
    }

    public Categorias insert(Categorias obj){
        obj.setId(null);
        return repo.save(obj);
    }

}
