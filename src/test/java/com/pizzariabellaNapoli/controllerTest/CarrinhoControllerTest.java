package com.pizzariabellaNapoli.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzariabellaNapoli.controller.CarrinhoController;
import com.pizzariabellaNapoli.domain.Carrinho;
import com.pizzariabellaNapoli.service.CarrinhoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Description of CarrinhoControllerTest
 * Created by calle on 20/12/2023.
 */
public class CarrinhoControllerTest {

    @Mock
    private CarrinhoService carrinhoService;

    @InjectMocks
    private CarrinhoController carrinhoController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(carrinhoController).build();
    }

    @Test
    public void salvarCarrinhoSucessoTest() throws Exception {
        Carrinho carrinho = new Carrinho();
        carrinho.setId(1L);

        when(carrinhoService.salvarCarrinho(any())).thenReturn(carrinho);

        mockMvc.perform(post("/api/carrinhos/salvar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(carrinho)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));

        verify(carrinhoService, times(1)).salvarCarrinho(any());
    }

    @Test
    public void salvarCarrinhoFalhaTest() throws Exception {
        Carrinho carrinho = new Carrinho();

        when(carrinhoService.salvarCarrinho(any())).thenThrow(new RuntimeException("Erro interno"));

        mockMvc.perform(post("/api/carrinhos/salvar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(carrinho)))
                .andExpect(status().isInternalServerError());

        verify(carrinhoService, times(1)).salvarCarrinho(any());
    }

    @Test
    public void salvarCarrinhoPizzaNaoEncontradaTest() throws Exception {
        Carrinho carrinho = new Carrinho();

        when(carrinhoService.salvarCarrinho(any())).thenThrow(new IllegalArgumentException("Pizza não encontrada"));

        mockMvc.perform(post("/api/carrinhos/salvar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(carrinho)))
                .andExpect(status().isBadRequest());

        verify(carrinhoService, times(1)).salvarCarrinho(any());
    }

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}