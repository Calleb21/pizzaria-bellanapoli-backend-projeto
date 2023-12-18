package com.pizzariabellaNapoli.serviceTest;

import com.pizzariabellaNapoli.domain.*;
import com.pizzariabellaNapoli.repository.CupomFiscalRepository;
import com.pizzariabellaNapoli.service.CupomFiscalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Description of CupomFiscalServiceTest
 * Created by calle on 18/12/2023.
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
                new CupomFiscal(1L, "Informacoes1", LocalDateTime.now(), "Pagamento1"),
                new CupomFiscal(2L, "Informacoes2", LocalDateTime.now(), "Pagamento2")
        );

        when(cupomFiscalRepository.findAll()).thenReturn(cuponsFiscais);
        List<CupomFiscal> result = cupomFiscalService.listarTodosCuponsFiscais();
        assertEquals(cuponsFiscais, result);
    }

    @Test
    public void buscarCupomFiscalPorId_ExistenteTest() {
        Long id = 1L;
        CupomFiscal cupomFiscal = new CupomFiscal(id, "Informacoes", LocalDateTime.now(), "Pagamento");
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
        CupomFiscal cupomFiscal = new CupomFiscal(null, "Informacoes", LocalDateTime.now(), "Pagamento");
        CupomFiscal cupomFiscal1 = new CupomFiscal(1L, "Informacoes", LocalDateTime.now(), "Pagamento");
        when(cupomFiscalRepository.save(cupomFiscal)).thenReturn(cupomFiscal1);

        CupomFiscal result = cupomFiscalService.salvarCupomFiscal(cupomFiscal);
        assertNotNull(result.getId());
        assertEquals(cupomFiscal1.getInformacoesPedido(), result.getInformacoesPedido());
        assertEquals(cupomFiscal1.getHorario(), result.getHorario());
        assertEquals(cupomFiscal1.getFormaPagamento(), result.getFormaPagamento());
    }

    @Test
    public void excluirCupomFiscalTest() {
        Long id = 1L;
        cupomFiscalService.excluirCupomFiscal(id);
        verify(cupomFiscalRepository, times(1)).deleteById(id);
    }

    @Test
    public void gerarCupomFiscalTest() {
        List<ItemPedido> itens = new ArrayList<>();
        ItemPedido item1 = new ItemPedido(1L, 2, new Pizza(1L, "Pizza1", "Ingredientes1", BigDecimal.valueOf(20.0)), null);
        ItemPedido item2 = new ItemPedido(2L, 1, new Pizza(2L, "Pizza2", "Ingredientes2", BigDecimal.valueOf(15.0)), null);
        itens.add(item1);
        itens.add(item2);

        Funcionario funcionario = new Funcionario(1L, "Calleb", "calleb@email", "calleb123");

        Pedido pedido = new Pedido(1L, funcionario, StatusPedido.EM_PRODUCAO, LocalDateTime.now(), BigDecimal.valueOf(35.0), "Cartão", itens);

        assertNotNull(pedido.getItens());
        cupomFiscalService.gerarCupomFiscal(pedido);
    }
}