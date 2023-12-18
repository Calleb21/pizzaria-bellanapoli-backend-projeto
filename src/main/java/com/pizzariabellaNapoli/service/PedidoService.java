package com.pizzariabellaNapoli.service;

import com.pizzariabellaNapoli.domain.Pedido;
import com.pizzariabellaNapoli.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Description of PedidoService
 * Created by calle on 18/12/2023.
 */
@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listarTodosPedidos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido salvarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void excluirPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
