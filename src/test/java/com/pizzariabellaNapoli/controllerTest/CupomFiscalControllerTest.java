package com.pizzariabellaNapoli.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pizzariabellaNapoli.controller.CupomFiscalController;
import com.pizzariabellaNapoli.domain.CupomFiscal;
import com.pizzariabellaNapoli.service.CupomFiscalService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Description of CupomFiscalControllerTest
 * Created by calle on 18/12/2023.
 */
public class CupomFiscalControllerTest {

    @Mock
    private CupomFiscalService cupomFiscalService;

    @InjectMocks
    private CupomFiscalController cupomFiscalController;

    private MockMvc mockMvc;

    public CupomFiscalControllerTest() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(cupomFiscalController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper()))
                .build();
    }

    private ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Test
    void listarTodosCuponsFiscais_DeveRetornarListaVazia() throws Exception {
        when(cupomFiscalService.listarTodosCuponsFiscais()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/cuponsfiscais/listar"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isEmpty());

        verify(cupomFiscalService, times(1)).listarTodosCuponsFiscais();
        verifyNoMoreInteractions(cupomFiscalService);
    }

    @Test
    void buscarCupomFiscalPorId_Encontrado_DeveRetornarCupomFiscal() throws Exception {
        CupomFiscal cupomFiscal = new CupomFiscal(1L, "Informacoes do pedido", LocalDateTime.now(), "Dinheiro");
        when(cupomFiscalService.buscarCupomFiscalPorId(1L)).thenReturn(Optional.of(cupomFiscal));

        mockMvc.perform(get("/api/cuponsfiscais/buscar/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));

        verify(cupomFiscalService, times(1)).buscarCupomFiscalPorId(1L);
        verifyNoMoreInteractions(cupomFiscalService);
    }

    @Test
    void buscarCupomFiscalPorId_NaoEncontrado_DeveRetornarNotFound() throws Exception {
        when(cupomFiscalService.buscarCupomFiscalPorId(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/cuponsfiscais/buscar/1"))
                .andExpect(status().isNotFound());

        verify(cupomFiscalService, times(1)).buscarCupomFiscalPorId(1L);
        verifyNoMoreInteractions(cupomFiscalService);
    }

    @Test
    void gerarCupomFiscal_DeveRetornarNovoCupomFiscal() throws Exception {
        CupomFiscal cupomFiscal = new CupomFiscal();
        cupomFiscal.setInformacoesPedido("Teste");
        cupomFiscal.setHorario(LocalDateTime.now());
        cupomFiscal.setFormaPagamento("Dinheiro");

        String cupomFiscalJson = objectMapper().writeValueAsString(cupomFiscal);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cuponsfiscais/gerar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cupomFiscalJson))
                .andExpect(status().isCreated());
    }


    @Test
    void cancelarCupomFiscal_DeveRetornarNoContent() throws Exception {
        // Assume that the cupom fiscal with ID 1 exists
        doNothing().when(cupomFiscalService).excluirCupomFiscal(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/cuponsfiscais/cancelar/1"))
                .andExpect(status().isNoContent());

        verify(cupomFiscalService, times(1)).excluirCupomFiscal(1L);
        verifyNoMoreInteractions(cupomFiscalService);
    }
}

