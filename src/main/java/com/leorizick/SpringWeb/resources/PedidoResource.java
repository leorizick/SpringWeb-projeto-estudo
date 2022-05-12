package com.leorizick.SpringWeb.resources;

import com.leorizick.SpringWeb.domain.Categorias;
import com.leorizick.SpringWeb.domain.Pedido;
import com.leorizick.SpringWeb.dto.CategoriasDTO;
import com.leorizick.SpringWeb.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Pedido obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
//        return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping()
    public ResponseEntity<Page<Pedido>> findPages(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "line", defaultValue = "10")Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "date")String orderBy,
            @RequestParam(value = "direction", defaultValue = "DESC")String direction) {
        Page<Pedido> list = service.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }
}


