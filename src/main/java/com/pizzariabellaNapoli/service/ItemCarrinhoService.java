package com.pizzariabellaNapoli.service;

import com.pizzariabellaNapoli.domain.ItemCarrinho;
import com.pizzariabellaNapoli.repository.ItemCarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Description of ItemCarrinhoService
 * Created by calle on 20/12/2023.
 */
@Service
public class ItemCarrinhoService {

    private final ItemCarrinhoRepository itemCarrinhoRepository;

    @Autowired
    public ItemCarrinhoService(ItemCarrinhoRepository itemCarrinhoRepository) {
        this.itemCarrinhoRepository = itemCarrinhoRepository;
    }

    public List<ItemCarrinho> listarTodosItensCarrinho() {
        return itemCarrinhoRepository.findAll();
    }

    public Optional<ItemCarrinho> buscarItemCarrinhoPorId(Long id) {
        return itemCarrinhoRepository.findById(id);
    }

    public ItemCarrinho salvarItemCarrinho(ItemCarrinho itemCarrinho) {
        return itemCarrinhoRepository.save(itemCarrinho);
    }

    public void excluirItemCarrinho(Long id) {
        itemCarrinhoRepository.deleteById(id);
    }
}


