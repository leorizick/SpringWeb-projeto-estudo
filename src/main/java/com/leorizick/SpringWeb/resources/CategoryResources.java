package com.leorizick.SpringWeb.resources;

import com.leorizick.SpringWeb.domain.Category;
import com.leorizick.SpringWeb.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryResources {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Category obj = categoryService.find(id);
        return ResponseEntity.ok().body(obj);
    }
}


