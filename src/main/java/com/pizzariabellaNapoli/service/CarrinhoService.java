package com.pizzariabellaNapoli.service;

import com.pizzariabellaNapoli.domain.Carrinho;
import com.pizzariabellaNapoli.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description of CarrinhoService
 * Created by calle on 20/12/2023.
 */
@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final PizzaService pizzaService;

    @Autowired
    public CarrinhoService(CarrinhoRepository carrinhoRepository, PizzaService pizzaService) {
        this.carrinhoRepository = carrinhoRepository;
        this.pizzaService = pizzaService;
    }

    @Transactional
    public Carrinho salvarCarrinho(Carrinho carrinho) {
        carrinho.getItens().forEach(item -> {
            item.setCarrinho(carrinho);
            item.setPizza(pizzaService.buscarPizzaPorId(item.getPizza().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Pizza não encontrada")));
        });

        return carrinhoRepository.save(carrinho);
    }
}
