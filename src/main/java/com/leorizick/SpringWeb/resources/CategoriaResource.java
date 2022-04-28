package com.leorizick.SpringWeb.resources;

import com.leorizick.SpringWeb.domain.Categorias;
import com.leorizick.SpringWeb.dto.CategoriasDTO;
import com.leorizick.SpringWeb.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categorias> find(@PathVariable Integer id) {
        Categorias obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<CategoriasDTO>> findAll() {
        List<Categorias> list = service.findAll();
        List<CategoriasDTO> listDto = list.stream().map(obj -> new CategoriasDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoriasDTO objDto){
        Categorias obj = service.fromDto(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody CategoriasDTO objDto, @PathVariable Integer id){
        Categorias obj = service.fromDto(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete (@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<CategoriasDTO>> findPages(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "line", defaultValue = "10")Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name")String orderBy,
            @RequestParam(value = "direction", defaultValue = "DESC")String direction) {
        Page<Categorias> list = service.findPage(page, linesPerPage, orderBy, direction);
        Page<CategoriasDTO> listDto = list.map(obj -> new CategoriasDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }
}


