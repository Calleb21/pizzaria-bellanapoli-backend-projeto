package com.pizzariabellaNapoli.service;

import com.pizzariabellaNapoli.domain.ItemPedido;
import com.pizzariabellaNapoli.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Description of ItemPedidoService
 * Created by calle on 18/12/2023.
 */
@Service
public class ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;

    @Autowired
    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public List<ItemPedido> listarTodosItensPedido() {
        return itemPedidoRepository.findAll();
    }

    public Optional<ItemPedido> buscarItemPedidoPorId(Long id) {
        return itemPedidoRepository.findById(id);
    }

    public ItemPedido salvarItemPedido(ItemPedido itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }

    public void excluirItemPedido(Long id) {
        itemPedidoRepository.deleteById(id);
    }
}
