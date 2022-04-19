package com.leorizick.SpringWeb.resources;

import com.leorizick.SpringWeb.domain.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryResources {

    @GetMapping
    public List<Category> list(){

        List<Category> list = new ArrayList<>();
        Category cat1 = new Category(1, "Informatica");
        Category cat2 = new Category(2, "Eletronicos");
        list.add(cat1);
        list.add(cat2);

        return list;
    }
}
