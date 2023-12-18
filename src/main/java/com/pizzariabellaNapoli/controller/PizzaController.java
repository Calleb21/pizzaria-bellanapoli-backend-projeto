package com.pizzariabellaNapoli.controller;

import com.pizzariabellaNapoli.domain.Pizza;
import com.pizzariabellaNapoli.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description of PizzaController
 * Created by calle on 18/12/2023.
 */
@RestController
@RequestMapping("/api/pizzas")
@CrossOrigin(origins = "http://localhost:5173")
public class PizzaController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Pizza>> listarTodasPizzas() {
        List<Pizza> pizzas = pizzaService.listarTodasPizzas();
        return new ResponseEntity<>(pizzas, HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Pizza> buscarPizzaPorId(@PathVariable Long id) {
        return pizzaService.buscarPizzaPorId(id)
                .map(pizza -> new ResponseEntity<>(pizza, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Pizza> salvarPizza(@RequestBody Pizza pizza) {
        Pizza novaPizza = pizzaService.salvarPizza(pizza);
        return new ResponseEntity<>(novaPizza, HttpStatus.CREATED);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirPizza(@PathVariable Long id) {
        pizzaService.excluirPizza(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
