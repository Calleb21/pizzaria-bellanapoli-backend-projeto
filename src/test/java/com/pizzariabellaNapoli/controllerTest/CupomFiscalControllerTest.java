package com.pizzariabellaNapoli.controllerTest;

import com.pizzariabellaNapoli.controller.CupomFiscalController;
import com.pizzariabellaNapoli.domain.Carrinho;
import com.pizzariabellaNapoli.domain.CupomFiscal;
import com.pizzariabellaNapoli.service.CupomFiscalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

/**
 * Description of CupomFiscalControllerTest
 * Created by calle on 20/12/2023.
 */
public class CupomFiscalControllerTest {

    @Mock
    private CupomFiscalService cupomFiscalService;

    @InjectMocks
    private CupomFiscalController cupomFiscalController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarTodosCuponsFiscais_DeveRetornarListaDeCuponsFiscais() {
        CupomFiscal cupomFiscal1 = new CupomFiscal(1L, LocalDateTime.now(), "Cartao", new Carrinho());
        CupomFiscal cupomFiscal2 = new CupomFiscal(2L, LocalDateTime.now(), "Dinheiro", new Carrinho());
        List<CupomFiscal> listaCuponsFiscais = Arrays.asList(cupomFiscal1, cupomFiscal2);

        when(cupomFiscalService.listarTodosCuponsFiscais()).thenReturn(listaCuponsFiscais);

        ResponseEntity<List<CupomFiscal>> responseEntity = cupomFiscalController.listarTodosCuponsFiscais();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(listaCuponsFiscais, responseEntity.getBody());
    }

    @Test
    void buscarCupomFiscalPorId_Encontrado_DeveRetornarCupomFiscal() {
        Long idCupomFiscal = 1L;
        CupomFiscal cupomFiscal = new CupomFiscal(idCupomFiscal, LocalDateTime.now(), "Cartao", new Carrinho());

        when(cupomFiscalService.buscarCupomFiscalPorId(idCupomFiscal)).thenReturn(Optional.of(cupomFiscal));

        ResponseEntity<CupomFiscal> responseEntity = cupomFiscalController.buscarCupomFiscalPorId(idCupomFiscal);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(cupomFiscal, responseEntity.getBody());
    }

    @Test
    void buscarCupomFiscalPorId_NaoEncontrado_DeveRetornarNotFound() {
        Long idCupomFiscal = 1L;

        when(cupomFiscalService.buscarCupomFiscalPorId(idCupomFiscal)).thenReturn(Optional.empty());

        ResponseEntity<CupomFiscal> responseEntity = cupomFiscalController.buscarCupomFiscalPorId(idCupomFiscal);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    void salvarCupomFiscal_DeveRetornarNovoCupomFiscal() {
        CupomFiscal cupomFiscal = new CupomFiscal(1L, LocalDateTime.now(), "Cartao", new Carrinho());

        when(cupomFiscalService.salvarCupomFiscal(any(CupomFiscal.class))).thenReturn(cupomFiscal);

        ResponseEntity<CupomFiscal> responseEntity = cupomFiscalController.salvarCupomFiscal(cupomFiscal);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(cupomFiscal, responseEntity.getBody());
    }

    @Test
    void excluirCupomFiscal_DeveRetornarNoContent() {
        ResponseEntity<Void> responseEntity = cupomFiscalController.excluirCupomFiscal(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(cupomFiscalService, times(1)).excluirCupomFiscal(1L);
    }

    @Test
    void listarCuponsFiscaisPorCarrinho_DeveRetornarListaDeCuponsFiscais() {
        Long idCarrinho = 1L;
        Carrinho carrinho = new Carrinho();
        CupomFiscal cupomFiscal1 = new CupomFiscal(1L, LocalDateTime.now(), "Cartao", carrinho);
        CupomFiscal cupomFiscal2 = new CupomFiscal(2L, LocalDateTime.now(), "Dinheiro", carrinho);
        List<CupomFiscal> listaCuponsFiscais = Arrays.asList(cupomFiscal1, cupomFiscal2);

        when(cupomFiscalService.listarCuponsFiscaisPorCarrinho(carrinho)).thenReturn(listaCuponsFiscais);

        ResponseEntity<List<CupomFiscal>> responseEntity = cupomFiscalController.listarCuponsFiscaisPorCarrinho(idCarrinho);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(listaCuponsFiscais, responseEntity.getBody());
    }

    @Test
    void buscarCupomFiscalPorCarrinhoEId_Encontrado_DeveRetornarCupomFiscal() {
        Long idCarrinho = 1L;
        Long idCupomFiscal = 1L;
        Carrinho carrinho = new Carrinho();
        CupomFiscal cupomFiscal = new CupomFiscal(idCupomFiscal, LocalDateTime.now(), "Cartao", carrinho);

        when(cupomFiscalService.buscarCupomFiscalPorCarrinhoEId(carrinho, idCupomFiscal)).thenReturn(Optional.of(cupomFiscal));

        ResponseEntity<CupomFiscal> responseEntity = cupomFiscalController.buscarCupomFiscalPorCarrinhoEId(idCarrinho, idCupomFiscal);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(cupomFiscal, responseEntity.getBody());
    }

    @Test
    void buscarCupomFiscalPorCarrinhoEId_NaoEncontrado_DeveRetornarNotFound() {
        Long idCarrinho = 1L;
        Long idCupomFiscal = 1L;
        Carrinho carrinho = new Carrinho();

        when(cupomFiscalService.buscarCupomFiscalPorCarrinhoEId(carrinho, idCupomFiscal)).thenReturn(Optional.empty());

        ResponseEntity<CupomFiscal> responseEntity = cupomFiscalController.buscarCupomFiscalPorCarrinhoEId(idCarrinho, idCupomFiscal);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }
}
