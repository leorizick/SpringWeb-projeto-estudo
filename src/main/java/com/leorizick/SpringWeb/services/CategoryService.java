package com.leorizick.SpringWeb.services;

import com.leorizick.SpringWeb.domain.Category;
import com.leorizick.SpringWeb.repositories.CategoryRepository;
import com.leorizick.SpringWeb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public Category find(Integer id) {
        Optional<Category> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
    }


}
