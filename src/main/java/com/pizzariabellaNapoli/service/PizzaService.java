package com.pizzariabellaNapoli.service;

import com.pizzariabellaNapoli.domain.Pizza;
import com.pizzariabellaNapoli.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Description of PizzaService
 * Created by calle on 18/12/2023.
 */@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<Pizza> listarTodasPizzas() {
        return pizzaRepository.findAll();
    }

    public Optional<Pizza> buscarPizzaPorId(Long id) {
        return pizzaRepository.findById(id);
    }

    public Pizza salvarPizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public void excluirPizza(Long id) {
        pizzaRepository.deleteById(id);
    }
}
