package com.pizzariabellaNapoli.service;

import com.pizzariabellaNapoli.domain.Carrinho;
import com.pizzariabellaNapoli.domain.Funcionario;
import com.pizzariabellaNapoli.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Description of CarrinhoService
 * Created by calle on 20/12/2023.
 */@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final PizzaService pizzaService;

    @Autowired
    public CarrinhoService(CarrinhoRepository carrinhoRepository, PizzaService pizzaService) {
        this.carrinhoRepository = carrinhoRepository;
        this.pizzaService = pizzaService;
    }

    public List<Carrinho> listarTodosCarrinhos() {
        return carrinhoRepository.findAll();
    }

    public Optional<Carrinho> buscarCarrinhoPorId(Long id) {
        return carrinhoRepository.findById(id);
    }

    public Carrinho salvarCarrinho(Carrinho carrinho) {
        carrinho.getItens().forEach(item -> {
            item.setCarrinho(carrinho);
            item.setPizza(pizzaService.buscarPizzaPorId(item.getPizza().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Pizza não encontrada")));
        });
        return carrinhoRepository.save(carrinho);
    }

    public void excluirCarrinho(Long id) {
        carrinhoRepository.deleteById(id);
    }

    public List<Carrinho> listarCarrinhosPorFuncionario(Funcionario funcionario) {
        return carrinhoRepository.findByFuncionario(funcionario);
    }

    public Optional<Carrinho> buscarCarrinhoPorFuncionarioEId(Funcionario funcionario, Long id) {
        return carrinhoRepository.findByFuncionarioAndId(funcionario, id);
    }
}
