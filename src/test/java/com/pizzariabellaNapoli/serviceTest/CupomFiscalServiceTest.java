package com.pizzariabellaNapoli.serviceTest;

import com.pizzariabellaNapoli.domain.Carrinho;
import com.pizzariabellaNapoli.domain.CupomFiscal;
import com.pizzariabellaNapoli.repository.CupomFiscalRepository;
import com.pizzariabellaNapoli.service.CupomFiscalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Description of CupomFiscalServiceTest
 * Created by calle on 20/12/2023.
 */
public class CupomFiscalServiceTest {

    @Mock
    private CupomFiscalRepository cupomFiscalRepository;

    @InjectMocks
    private CupomFiscalService cupomFiscalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void listarTodosCuponsFiscaisTest() {
        List<CupomFiscal> cuponsFiscais = Arrays.asList(
                new CupomFiscal(1L, "Informacoes1", LocalDateTime.now(), "Pagamento1", new Carrinho()),
                new CupomFiscal(2L, "Informacoes2", LocalDateTime.now(), "Pagamento2", new Carrinho())
        );

        when(cupomFiscalRepository.findAll()).thenReturn(cuponsFiscais);
        List<CupomFiscal> result = cupomFiscalService.listarTodosCuponsFiscais();
        assertEquals(cuponsFiscais, result);
    }
    @Test
    public void buscarCupomFiscalPorId_ExistenteTest() {
        Long id = 1L;
        CupomFiscal cupomFiscal = new CupomFiscal(id, "Informacoes", LocalDateTime.now(), "Pagamento", new Carrinho());
        when(cupomFiscalRepository.findById(id)).thenReturn(Optional.of(cupomFiscal));

        Optional<CupomFiscal> result = cupomFiscalService.buscarCupomFiscalPorId(id);
        assertTrue(result.isPresent());
        assertEquals(cupomFiscal, result.get());
    }

    @Test
    public void buscarCupomFiscalPorId_NaoExistenteTest() {
        Long id = 1L;
        when(cupomFiscalRepository.findById(id)).thenReturn(Optional.empty());

        Optional<CupomFiscal> result = cupomFiscalService.buscarCupomFiscalPorId(id);
        assertTrue(result.isEmpty());
    }

    @Test
    public void salvarCupomFiscalTest() {
        CupomFiscal cupomFiscal = new CupomFiscal(null, "Informacoes", LocalDateTime.now(), "Pagamento", new Carrinho());
        CupomFiscal cupomFiscal1 = new CupomFiscal(1L, "Informacoes", LocalDateTime.now(), "Pagamento", new Carrinho());
        when(cupomFiscalRepository.save(cupomFiscal)).thenReturn(cupomFiscal1);

        CupomFiscal result = cupomFiscalService.salvarCupomFiscal(cupomFiscal);
        assertNotNull(result.getId());
        assertEquals(cupomFiscal1.getInformacaoesPedido(), result.getInformacaoesPedido());
        assertEquals(cupomFiscal1.getHorario(), result.getHorario());
        assertEquals(cupomFiscal1.getFormaPagamento(), result.getFormaPagamento());
        assertEquals(cupomFiscal1.getCarrinho(), result.getCarrinho());
    }

    @Test
    public void excluirCupomFiscalTest() {
        Long id = 1L;
        cupomFiscalService.excluirCupomFiscal(id);
        verify(cupomFiscalRepository, times(1)).deleteById(id);
    }

    @Test
    public void listarCuponsFiscaisPorCarrinhoTest() {
        Carrinho carrinho = new Carrinho(); // Ajuste conforme necessário
        List<CupomFiscal> cuponsFiscais = Arrays.asList(
                new CupomFiscal(1L, "Informacoes1", LocalDateTime.now(), "Pagamento1", carrinho),
                new CupomFiscal(2L, "Informacoes2", LocalDateTime.now(), "Pagamento2", carrinho)
        );

        when(cupomFiscalRepository.findByCarrinho(carrinho)).thenReturn(cuponsFiscais);
        List<CupomFiscal> result = cupomFiscalService.listarCuponsFiscaisPorCarrinho(carrinho);
        assertEquals(cuponsFiscais, result);
    }

    @Test
    public void buscarCupomFiscalPorCarrinhoEId_ExistenteTest() {
        Long id = 1L;
        Carrinho carrinho = new Carrinho(); // Ajuste conforme necessário
        CupomFiscal cupomFiscal = new CupomFiscal(id, "Informacoes", LocalDateTime.now(), "Pagamento", carrinho);
        when(cupomFiscalRepository.findByCarrinhoAndId(carrinho, id)).thenReturn(Optional.of(cupomFiscal));

        Optional<CupomFiscal> result = cupomFiscalService.buscarCupomFiscalPorCarrinhoEId(carrinho, id);
        assertTrue(result.isPresent());
        assertEquals(cupomFiscal, result.get());
    }

    @Test
    public void buscarCupomFiscalPorCarrinhoEId_NaoExistenteTest() {
        Long id = 1L;
        Carrinho carrinho = new Carrinho(); // Ajuste conforme necessário
        when(cupomFiscalRepository.findByCarrinhoAndId(carrinho, id)).thenReturn(Optional.empty());

        Optional<CupomFiscal> result = cupomFiscalService.buscarCupomFiscalPorCarrinhoEId(carrinho, id);
        assertTrue(result.isEmpty());
    }

}
