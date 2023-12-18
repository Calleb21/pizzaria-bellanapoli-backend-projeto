package com.pizzariabellaNapoli.serviceTest;

import com.pizzariabellaNapoli.domain.ItemPedido;
import com.pizzariabellaNapoli.domain.Pedido;
import com.pizzariabellaNapoli.domain.Pizza;
import com.pizzariabellaNapoli.repository.ItemPedidoRepository;
import com.pizzariabellaNapoli.service.ItemPedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Description of ItemPedidoServiceTest
 * Created by calle on 18/12/2023.
 */
class ItemPedidoServiceTest {

    @Mock
    private ItemPedidoRepository itemPedidoRepository;

    @InjectMocks
    private ItemPedidoService itemPedidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void listarTodosItensPedidoTest() {
        // Criar pizzas e pedidos simulados
        Pizza pizza1 = new Pizza(1L, "Pizza1", "Ingredientes1", BigDecimal.valueOf(20.0));
        Pizza pizza2 = new Pizza(2L, "Pizza2", "Ingredientes2", BigDecimal.valueOf(15.0));

        Pedido pedido1 = new Pedido(1L, null, null, LocalDateTime.now(), BigDecimal.valueOf(35.0), "Cartão", null);
        Pedido pedido2 = new Pedido(2L, null, null, LocalDateTime.now(), BigDecimal.valueOf(25.0), "Dinheiro", null);

        ItemPedido item1 = new ItemPedido(1L, 2, pizza1, pedido1);
        ItemPedido item2 = new ItemPedido(2L, 3, pizza2, pedido2);

        List<ItemPedido> itensSimulados = Arrays.asList(item1, item2);

        when(itemPedidoRepository.findAll()).thenReturn(itensSimulados);

        List<ItemPedido> itensRetornados = itemPedidoService.listarTodosItensPedido();
        assertEquals(itensSimulados, itensRetornados);
        verify(itemPedidoRepository, times(1)).findAll();
    }

    @Test
    void buscarItemPedidoPorIdTest() {
        ItemPedido itemSimulado = new ItemPedido(1L, 2, null, null);
        Optional<ItemPedido> itemOptional = Optional.of(itemSimulado);
        when(itemPedidoRepository.findById(1L)).thenReturn(itemOptional);

        Optional<ItemPedido> itemRetornado = itemPedidoService.buscarItemPedidoPorId(1L);
        assertEquals(itemOptional, itemRetornado);
        verify(itemPedidoRepository, times(1)).findById(1L);
    }

    @Test
    void salvarItemPedidoTest() {
        ItemPedido itemParaSalvar = new ItemPedido(1L, 2, null, null);
        when(itemPedidoRepository.save(itemParaSalvar)).thenReturn(itemParaSalvar);

        ItemPedido itemSalvo = itemPedidoService.salvarItemPedido(itemParaSalvar);
        assertEquals(itemParaSalvar, itemSalvo);
        verify(itemPedidoRepository, times(1)).save(itemParaSalvar);
    }

    @Test
    void excluirItemPedidoTest() {
        itemPedidoService.excluirItemPedido(1L);
        verify(itemPedidoRepository, times(1)).deleteById(1L);
    }
}
