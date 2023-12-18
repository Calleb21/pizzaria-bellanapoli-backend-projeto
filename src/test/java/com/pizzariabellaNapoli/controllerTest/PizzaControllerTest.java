package com.pizzariabellaNapoli.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzariabellaNapoli.controller.PizzaController;
import com.pizzariabellaNapoli.domain.Pizza;
import com.pizzariabellaNapoli.service.PizzaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Description of PizzaControllerTest
 * Created by calle on 18/12/2023.
 */
public class PizzaControllerTest {

    @Mock
    private PizzaService pizzaService;

    @InjectMocks
    private PizzaController pizzaController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(pizzaController).build();
    }

    @Test
    void listarTodasPizzas_DeveRetornarListaVazia() throws Exception {
        when(pizzaService.listarTodasPizzas()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pizzas/listar"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isEmpty());

        verify(pizzaService, times(1)).listarTodasPizzas();
        verifyNoMoreInteractions(pizzaService);
    }

    @Test
    void buscarPizzaPorId_Encontrado_DeveRetornarPizza() throws Exception {
        Pizza pizza = new Pizza(1L, "Margherita", "Tomato, Mozzarella, Basil", BigDecimal.valueOf(10.99));
        when(pizzaService.buscarPizzaPorId(1L)).thenReturn(Optional.of(pizza));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pizzas/buscar/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Margherita"));

        verify(pizzaService, times(1)).buscarPizzaPorId(1L);
        verifyNoMoreInteractions(pizzaService);
    }

    @Test
    void buscarPizzaPorId_NaoEncontrado_DeveRetornarNotFound() throws Exception {
        when(pizzaService.buscarPizzaPorId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pizzas/buscar/1"))
                .andExpect(status().isNotFound());

        verify(pizzaService, times(1)).buscarPizzaPorId(1L);
        verifyNoMoreInteractions(pizzaService);
    }

    @Test
    void salvarPizza_DeveRetornarNovaPizza() throws Exception {
        Pizza pizza = new Pizza(1L, "Pepperoni", "Pepperoni, Cheese", BigDecimal.valueOf(12.99));

        when(pizzaService.salvarPizza(any(Pizza.class))).thenReturn(pizza);

        ObjectMapper objectMapper = new ObjectMapper();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/pizzas/cadastrar")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pizza))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Pepperoni"));

        verify(pizzaService, times(1)).salvarPizza(any(Pizza.class));
        verifyNoMoreInteractions(pizzaService);
    }

    @Test
    void excluirPizza_DeveRetornarNoContent() throws Exception {
        doNothing().when(pizzaService).excluirPizza(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/pizzas/excluir/1"))
                .andExpect(status().isNoContent());

        verify(pizzaService, times(1)).excluirPizza(1L);
        verifyNoMoreInteractions(pizzaService);
    }
}
