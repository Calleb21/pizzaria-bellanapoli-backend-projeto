package com.pizzariabellaNapoli.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pizzariabellaNapoli.controller.PedidoController;
import com.pizzariabellaNapoli.domain.Pedido;
import com.pizzariabellaNapoli.domain.StatusPedido;
import com.pizzariabellaNapoli.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Description of PedidoControllerTest
 * Created by calle on 18/12/2023.
 */
public class PedidoControllerTest {

    @Mock
    private PedidoService pedidoService;

    @InjectMocks
    private PedidoController pedidoController;

    private MockMvc mockMvc;

    public PedidoControllerTest() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(pedidoController).build();
    }

    @Test
    void listarTodosPedidos_DeveRetornarListaVazia() throws Exception {
        when(pedidoService.listarTodosPedidos()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/pedidos/listar"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isEmpty());

        verify(pedidoService, times(1)).listarTodosPedidos();
        verifyNoMoreInteractions(pedidoService);
    }

    @Test
    void buscarPedidoPorId_Encontrado_DeveRetornarPedido() throws Exception {
        Pedido pedido = new Pedido(1L, null, StatusPedido.EM_PRODUCAO, LocalDateTime.now(), BigDecimal.TEN, "Dinheiro", Collections.emptyList());
        when(pedidoService.buscarPedidoPorId(1L)).thenReturn(Optional.of(pedido));

        mockMvc.perform(get("/api/pedidos/buscar/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));

        verify(pedidoService, times(1)).buscarPedidoPorId(1L);
        verifyNoMoreInteractions(pedidoService);
    }

    @Test
    void buscarPedidoPorId_NaoEncontrado_DeveRetornarNotFound() throws Exception {
        when(pedidoService.buscarPedidoPorId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/pedidos/buscar/1"))
                .andExpect(status().isNotFound());

        verify(pedidoService, times(1)).buscarPedidoPorId(1L);
        verifyNoMoreInteractions(pedidoService);
    }

    @Test
    void salvarPedido_DeveRetornarNovoPedido() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setHorario(LocalDateTime.now());

        when(pedidoService.salvarPedido(Mockito.any(Pedido.class))).thenReturn(pedido);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/pedidos/salvar")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pedido))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        Pedido respostaPedido = objectMapper.readValue(response.getContentAsString(), Pedido.class);
        assertEquals(1L, respostaPedido.getId());

        verify(pedidoService, times(1)).salvarPedido(Mockito.any(Pedido.class));
        verifyNoMoreInteractions(pedidoService);
    }

    @Test
    void excluirPedido_DeveRetornarNoContent() throws Exception {
        mockMvc.perform(delete("/api/pedidos/excluir/1"))
                .andExpect(status().isNoContent());

        verify(pedidoService, times(1)).excluirPedido(1L);
        verifyNoMoreInteractions(pedidoService);
    }
}
