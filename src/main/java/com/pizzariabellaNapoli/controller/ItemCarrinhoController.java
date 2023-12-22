package com.pizzariabellaNapoli.controller;

import com.pizzariabellaNapoli.domain.ItemCarrinho;
import com.pizzariabellaNapoli.service.ItemCarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description of ItemCarrinho
 * Created by calle on 21/12/2023.
 */
@RestController
@RequestMapping("/api/itens-carrinho")
@CrossOrigin(origins = "http://localhost:5173", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ItemCarrinhoController {

    private final ItemCarrinhoService itemCarrinhoService;

    @Autowired
    public ItemCarrinhoController(ItemCarrinhoService itemCarrinhoService) {
        this.itemCarrinhoService = itemCarrinhoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ItemCarrinho>> listarTodosItensCarrinho() {
        List<ItemCarrinho> itensCarrinho = itemCarrinhoService.listarTodosItensCarrinho();
        return new ResponseEntity<>(itensCarrinho, HttpStatus.OK);
    }

    @PostMapping("/salvar")
    public ResponseEntity<ItemCarrinho> salvarItemCarrinho(@RequestBody ItemCarrinho itemCarrinho) {
        ItemCarrinho novoItemCarrinho = itemCarrinhoService.salvarItemCarrinho(itemCarrinho);
        return new ResponseEntity<>(novoItemCarrinho, HttpStatus.CREATED);
    }
}
