package com.pizzariabellaNapoli.controller;

import com.pizzariabellaNapoli.domain.ItemPedido;
import com.pizzariabellaNapoli.service.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description of ItemPedidoController
 * Created by calle on 18/12/2023.
 */
@RestController
@RequestMapping("/api/itempedidos")
@CrossOrigin(origins = "http://localhost:5173", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ItemPedidoController {

    private final ItemPedidoService itemPedidoService;

    @Autowired
    public ItemPedidoController(ItemPedidoService itemPedidoService) {
        this.itemPedidoService = itemPedidoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ItemPedido>> listarTodosItensPedido() {
        List<ItemPedido> itensPedido = itemPedidoService.listarTodosItensPedido();
        return new ResponseEntity<>(itensPedido, HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ItemPedido> buscarItemPedidoPorId(@PathVariable Long id) {
        return itemPedidoService.buscarItemPedidoPorId(id)
                .map(itemPedido -> new ResponseEntity<>(itemPedido, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ItemPedido> adicionarItemPedido(@RequestBody ItemPedido itemPedido) {
        ItemPedido novoItemPedido = itemPedidoService.salvarItemPedido(itemPedido);
        return new ResponseEntity<>(novoItemPedido, HttpStatus.CREATED);
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> removerItemPedido(@PathVariable Long id) {
        itemPedidoService.excluirItemPedido(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
