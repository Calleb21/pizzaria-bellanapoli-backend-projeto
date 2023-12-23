package com.pizzariabellaNapoli.controller;

import com.pizzariabellaNapoli.domain.Carrinho;
import com.pizzariabellaNapoli.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Description of CarrinhoController
 * Created by calle on 20/12/2023.
 */
@RestController
@RequestMapping("/api/carrinhos")
@CrossOrigin(origins = "http://localhost:5173", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CarrinhoController {

    private final CarrinhoService carrinhoService;


    @Autowired
    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @PostMapping("/salvar")
    public ResponseEntity<Carrinho> salvarCarrinho(@RequestBody Carrinho carrinho) {
        try {
            Carrinho carrinhoSalvo = carrinhoService.salvarCarrinho(carrinho);
            return new ResponseEntity<>(carrinhoSalvo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
