package com.leorizick.SpringWeb.resources;

import com.leorizick.SpringWeb.domain.Produto;
import com.leorizick.SpringWeb.dto.CategoriasDTO;
import com.leorizick.SpringWeb.dto.ProdutoDTO;
import com.leorizick.SpringWeb.resources.utils.URL;
import com.leorizick.SpringWeb.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Produto obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> findPages(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "categorias", defaultValue = "") String categorias,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "line", defaultValue = "10") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction) {
        String nomeDecoded = URL.decodeParam(name);
        List<Integer> ids = URL.decodeIntList(categorias);
        Page<Produto> list = service.search( nomeDecoded, ids,page, linesPerPage, orderBy, direction);
        Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }
}


