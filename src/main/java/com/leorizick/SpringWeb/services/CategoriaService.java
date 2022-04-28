package com.leorizick.SpringWeb.services;

import com.leorizick.SpringWeb.domain.Categorias;
import com.leorizick.SpringWeb.dto.CategoriasDTO;
import com.leorizick.SpringWeb.repositories.CategoriaRepository;
import com.leorizick.SpringWeb.services.exception.DataIntegrityException;
import com.leorizick.SpringWeb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Categorias update(Categorias obj){
        find(obj.getId());
        return repo.save(obj);
    }

    public void delete(Integer id){
        find(id);
        try {
            repo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
        }
    }

    public List<Categorias> findAll(){
       return repo.findAll();
    }

    public Page<Categorias> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Categorias fromDto(CategoriasDTO objDto){
        return new Categorias(objDto.getId(), objDto.getName());
    }
}
