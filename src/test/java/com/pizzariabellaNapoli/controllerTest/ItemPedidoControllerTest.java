package com.pizzariabellaNapoli.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzariabellaNapoli.controller.ItemPedidoController;
import com.pizzariabellaNapoli.domain.ItemPedido;
import com.pizzariabellaNapoli.service.ItemPedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Description of ItemPedidoControllerTest
 * Created by calle on 18/12/2023.
 */
public class ItemPedidoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ItemPedidoService itemPedidoService;

    @InjectMocks
    private ItemPedidoController itemPedidoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(itemPedidoController).build();
    }

    @Test
    void listarTodosItensPedido_DeveRetornarListaVazia() throws Exception {
        when(itemPedidoService.listarTodosItensPedido()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/itempedidos/listar"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isEmpty());

        verify(itemPedidoService, times(1)).listarTodosItensPedido();
        verifyNoMoreInteractions(itemPedidoService);
    }

    @Test
    void buscarItemPedidoPorId_Encontrado_DeveRetornarItemPedido() throws Exception {
        ItemPedido itemPedido = new ItemPedido(1L, 2, null, null);
        when(itemPedidoService.buscarItemPedidoPorId(1L)).thenReturn(Optional.of(itemPedido));

        mockMvc.perform(get("/api/itempedidos/buscar/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));

        verify(itemPedidoService, times(1)).buscarItemPedidoPorId(1L);
        verifyNoMoreInteractions(itemPedidoService);
    }

    @Test
    void buscarItemPedidoPorId_NaoEncontrado_DeveRetornarNotFound() throws Exception {
        when(itemPedidoService.buscarItemPedidoPorId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/itempedidos/buscar/1"))
                .andExpect(status().isNotFound());

        verify(itemPedidoService, times(1)).buscarItemPedidoPorId(1L);
        verifyNoMoreInteractions(itemPedidoService);
    }

    @Test
    void adicionarItemPedido_DeveRetornarNovoItemPedido() throws Exception {
        ItemPedido itemPedido = new ItemPedido(1L, 2, null, null);
        when(itemPedidoService.salvarItemPedido(any(ItemPedido.class))).thenReturn(itemPedido);

        mockMvc.perform(post("/api/itempedidos/adicionar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(itemPedido)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));

        verify(itemPedidoService, times(1)).salvarItemPedido(any(ItemPedido.class));
        verifyNoMoreInteractions(itemPedidoService);
    }

    @Test
    void removerItemPedido_DeveRetornarNoContent() throws Exception {
        mockMvc.perform(delete("/api/itempedidos/remover/1"))
                .andExpect(status().isNoContent());

        verify(itemPedidoService, times(1)).excluirItemPedido(1L);
        verifyNoMoreInteractions(itemPedidoService);
    }
}
