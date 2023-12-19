package com.pizzariabellaNapoli.serviceTest;

import com.pizzariabellaNapoli.domain.Carrinho;
import com.pizzariabellaNapoli.domain.ItemCarrinho;
import com.pizzariabellaNapoli.domain.Pizza;
import com.pizzariabellaNapoli.repository.ItemCarrinhoRepository;
import com.pizzariabellaNapoli.service.ItemCarrinhoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Description of ItemCarrinhoServiceTest
 * Created by calle on 20/12/2023.
 */
public class ItemCarrinhoServiceTest {

    @Mock
    private ItemCarrinhoRepository itemCarrinhoRepository;

    @InjectMocks
    private ItemCarrinhoService itemCarrinhoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void listarTodosItensCarrinhoTest() {
        List<ItemCarrinho> itensCarrinho = Arrays.asList(
                new ItemCarrinho(1L, 2, new Pizza(), new Carrinho()),
                new ItemCarrinho(2L, 1, new Pizza(), new Carrinho())
        );

        when(itemCarrinhoRepository.findAll()).thenReturn(itensCarrinho);
        List<ItemCarrinho> result = itemCarrinhoService.listarTodosItensCarrinho();
        assertEquals(itensCarrinho, result);
    }

    @Test
    public void buscarItemCarrinhoPorId_ExistenteTest() {
        Long id = 1L;
        ItemCarrinho itemCarrinho = new ItemCarrinho(id, 2, new Pizza(), new Carrinho());
        when(itemCarrinhoRepository.findById(id)).thenReturn(Optional.of(itemCarrinho));

        Optional<ItemCarrinho> result = itemCarrinhoService.buscarItemCarrinhoPorId(id);
        assertTrue(result.isPresent());
        assertEquals(itemCarrinho, result.get());
    }

    @Test
    public void buscarItemCarrinhoPorId_NaoExistenteTest() {
        Long id = 1L;
        when(itemCarrinhoRepository.findById(id)).thenReturn(Optional.empty());

        Optional<ItemCarrinho> result = itemCarrinhoService.buscarItemCarrinhoPorId(id);
        assertTrue(result.isEmpty());
    }

    @Test
    public void salvarItemCarrinhoTest() {
        ItemCarrinho itemCarrinho = new ItemCarrinho(null, 2, new Pizza(), new Carrinho());
        ItemCarrinho itemCarrinho1 = new ItemCarrinho(1L, 2, new Pizza(), new Carrinho());
        when(itemCarrinhoRepository.save(itemCarrinho)).thenReturn(itemCarrinho1);

        ItemCarrinho result = itemCarrinhoService.salvarItemCarrinho(itemCarrinho);
        assertNotNull(result.getId());
        assertEquals(itemCarrinho1.getQuantidade(), result.getQuantidade());
        assertEquals(itemCarrinho1.getPizza(), result.getPizza());
        assertEquals(itemCarrinho1.getCarrinho(), result.getCarrinho());
    }

    @Test
    public void excluirItemCarrinhoTest() {
        Long id = 1L;
        itemCarrinhoService.excluirItemCarrinho(id);
        verify(itemCarrinhoRepository, times(1)).deleteById(id);
    }
}
