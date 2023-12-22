package com.pizzariabellaNapoli.controllerTest;

import com.pizzariabellaNapoli.controller.ItemCarrinhoController;
import com.pizzariabellaNapoli.domain.Carrinho;
import com.pizzariabellaNapoli.domain.ItemCarrinho;
import com.pizzariabellaNapoli.domain.Pizza;
import com.pizzariabellaNapoli.service.ItemCarrinhoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Description of ItemCarrinhoControllerTest
 * Created by calle on 21/12/2023.
 */
public class ItemCarrinhoControllerTest {

    @Mock
    private ItemCarrinhoService itemCarrinhoService;

    @InjectMocks
    private ItemCarrinhoController itemCarrinhoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarTodosItensCarrinho_DeveRetornarListaDeItensCarrinho() {
        ItemCarrinho itemCarrinho1 = new ItemCarrinho(1L, 2, new Pizza(), new Carrinho());
        ItemCarrinho itemCarrinho2 = new ItemCarrinho(2L, 1, new Pizza(), new Carrinho());
        List<ItemCarrinho> listaItensCarrinho = Arrays.asList(itemCarrinho1, itemCarrinho2);

        when(itemCarrinhoService.listarTodosItensCarrinho()).thenReturn(listaItensCarrinho);

        ResponseEntity<List<ItemCarrinho>> responseEntity = itemCarrinhoController.listarTodosItensCarrinho();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(listaItensCarrinho, responseEntity.getBody());
    }

    @Test
    void salvarItemCarrinho_DeveRetornarNovoItemCarrinho() {
        ItemCarrinho itemCarrinho = new ItemCarrinho(1L, 2, new Pizza(), new Carrinho());

        when(itemCarrinhoService.salvarItemCarrinho(any(ItemCarrinho.class))).thenReturn(itemCarrinho);

        ResponseEntity<ItemCarrinho> responseEntity = itemCarrinhoController.salvarItemCarrinho(itemCarrinho);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(itemCarrinho, responseEntity.getBody());
    }

}
