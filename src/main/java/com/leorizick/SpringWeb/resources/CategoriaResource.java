package com.leorizick.SpringWeb.resources;

import com.leorizick.SpringWeb.domain.Categorias;
import com.leorizick.SpringWeb.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categoria")
public class CategoriaResource {

    @Autowired
    private CategoryService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Categorias obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }
}


