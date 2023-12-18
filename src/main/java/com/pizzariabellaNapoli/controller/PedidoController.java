package com.pizzariabellaNapoli.controller;

import com.pizzariabellaNapoli.domain.Pedido;
import com.pizzariabellaNapoli.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description of PedidoController
 * Created by calle on 18/12/2023.
 */
@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "http://localhost:5173")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Pedido>> listarTodosPedidos() {
        List<Pedido> pedidos = pedidoService.listarTodosPedidos();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Long id) {
        return pedidoService.buscarPedidoPorId(id)
                .map(pedido -> new ResponseEntity<>(pedido, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/salvar")
    public ResponseEntity<Pedido> salvarPedido(@RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoService.salvarPedido(pedido);
        return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirPedido(@PathVariable Long id) {
        pedidoService.excluirPedido(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
