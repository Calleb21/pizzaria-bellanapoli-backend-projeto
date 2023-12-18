package com.pizzariabellaNapoli.serviceTest;

import com.pizzariabellaNapoli.domain.*;
import com.pizzariabellaNapoli.repository.PedidoRepository;
import com.pizzariabellaNapoli.service.PedidoService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Description of PedidoServiceTest
 * Created by calle on 18/12/2023.
 */
public class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void listarTodosPedidosTest() {
        List<Pedido> pedidos = Arrays.asList(
                criarPedido(1L, StatusPedido.EM_PRODUCAO),
                criarPedido(2L, StatusPedido.DISPONIVEL)
        );
        when(pedidoRepository.findAll()).thenReturn(pedidos);

        List<Pedido> result = pedidoService.listarTodosPedidos();
        assertEquals(pedidos, result);
    }

    @Test
    void buscarPedidoPorId_ExistenteTest() {
        Long id = 1L;
        Pedido pedido = criarPedido(id, StatusPedido.EM_PRODUCAO);
        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedido));

        Optional<Pedido> result = pedidoService.buscarPedidoPorId(id);
        assertTrue(result.isPresent());
        assertEquals(pedido, result.get());
    }


    @Test
    void buscarPedidoPorId_NaoExistenteTest() {
        Long id = 1L;
        when(pedidoRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Pedido> result = pedidoService.buscarPedidoPorId(id);
        assertTrue(result.isEmpty());
    }

    @Test
    void salvarPedidoTest() {
        Pedido novoPedido = criarPedido(null, StatusPedido.EM_PRODUCAO);
        Pedido pedidoSalvo = criarPedido(1L, StatusPedido.EM_PRODUCAO);
        when(pedidoRepository.save(novoPedido)).thenReturn(pedidoSalvo);

        Pedido result = pedidoService.salvarPedido(novoPedido);
        assertNotNull(result.getId());
        assertEquals(pedidoSalvo.getStatus(), result.getStatus());
        assertEquals(pedidoSalvo.getHorario(), result.getHorario());
        assertEquals(pedidoSalvo.getPrecoTotal(), result.getPrecoTotal());
        assertEquals(pedidoSalvo.getFormaPagamento(), result.getFormaPagamento());
        assertEquals(pedidoSalvo.getItens(), result.getItens());
    }

    @Test
    public void excluirPedidoTest() {
        Long id = 1L;
        pedidoService.excluirPedido(id);
        verify(pedidoRepository, times(1)).deleteById(id);
    }

    private Pedido criarPedido(Long id, StatusPedido statusPedido) {
        Pedido pedido = new Pedido();
        pedido.setId(id);
        pedido.setFuncionario(new Funcionario());
        pedido.setStatus(statusPedido);
        pedido.setHorario(LocalDateTime.now());
        pedido.setPrecoTotal(BigDecimal.valueOf(50.0));
        pedido.setFormaPagamento("Dinheiro");
        pedido.setItens(Arrays.asList(criarItemPedido(1L, 2), criarItemPedido(2L, 3)));
        return pedido;
    }

    private ItemPedido criarItemPedido(long id, int quantidade) {
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(id);
        itemPedido.setQuantidade(quantidade);
        itemPedido.setPizza(new Pizza());
        return itemPedido;
    }
}
