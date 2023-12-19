package com.pizzariabellaNapoli.serviceTest;

import com.pizzariabellaNapoli.domain.Carrinho;
import com.pizzariabellaNapoli.domain.Funcionario;
import com.pizzariabellaNapoli.domain.ItemCarrinho;
import com.pizzariabellaNapoli.domain.Pizza;
import com.pizzariabellaNapoli.repository.CarrinhoRepository;
import com.pizzariabellaNapoli.service.CarrinhoService;
import com.pizzariabellaNapoli.service.PizzaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Description of CarrinhoServiceTest
 * Created by calle on 20/12/2023.
 */
public class CarrinhoServiceTest {

    @Mock
    private CarrinhoRepository carrinhoRepository;

    @Mock
    private PizzaService pizzaService;

    @InjectMocks
    private CarrinhoService carrinhoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void listarTodosCarrinhosTest() {
        List<Carrinho> carrinhos = Arrays.asList(
                new Carrinho(1L, new Funcionario(), List.of(new ItemCarrinho())),
                new Carrinho(2L, new Funcionario(), List.of(new ItemCarrinho()))
        );

        when(carrinhoRepository.findAll()).thenReturn(carrinhos);
        List<Carrinho> result = carrinhoService.listarTodosCarrinhos();
        assertEquals(carrinhos, result);
    }

    @Test
    public void buscarCarrinhoPorId_ExistenteTest() {
        Long id = 1L;
        Carrinho carrinho = new Carrinho(id, new Funcionario(), List.of(new ItemCarrinho()));
        when(carrinhoRepository.findById(id)).thenReturn(Optional.of(carrinho));

        Optional<Carrinho> result = carrinhoService.buscarCarrinhoPorId(id);
        assertTrue(result.isPresent());
        assertEquals(carrinho, result.get());
    }

    @Test
    public void buscarCarrinhoPorId_NaoExistenteTest() {
        Long id = 1L;
        when(carrinhoRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Carrinho> result = carrinhoService.buscarCarrinhoPorId(id);
        assertTrue(result.isEmpty());
    }

    @Test
    public void salvarCarrinhoTest() {
        Pizza pizza = new Pizza(1L, "img.png", "Margherita", "Tomate, mussarela, manjericão", BigDecimal.valueOf(25.0));
        Funcionario funcionario = new Funcionario();

        Carrinho carrinho = new Carrinho();
        carrinho.setId(1L);
        carrinho.setFuncionario(funcionario);

        ItemCarrinho itemCarrinho = new ItemCarrinho();
        itemCarrinho.setId(1L);
        itemCarrinho.setQuantidade(1);
        itemCarrinho.setPizza(pizza);
        itemCarrinho.setCarrinho(carrinho);

        carrinho.setItens(List.of(itemCarrinho));

        when(pizzaService.buscarPizzaPorId(anyLong())).thenReturn(Optional.of(pizza));
        when(carrinhoRepository.save(carrinho)).thenReturn(carrinho);

        Carrinho result = carrinhoService.salvarCarrinho(carrinho);

        assertNotNull(result.getId());
        assertEquals(carrinho.getFuncionario(), result.getFuncionario());
        assertEquals(carrinho.getItens(), result.getItens());
    }


    @Test
    public void excluirCarrinhoTest() {
        Long id = 1L;
        carrinhoService.excluirCarrinho(id);
        verify(carrinhoRepository, times(1)).deleteById(id);
    }

    @Test
    public void listarCarrinhosPorFuncionarioTest() {
        Funcionario funcionario = new Funcionario();
        List<Carrinho> carrinhos = Arrays.asList(
                new Carrinho(1L, funcionario, List.of(new ItemCarrinho())),
                new Carrinho(2L, funcionario, List.of(new ItemCarrinho()))
        );

        when(carrinhoRepository.findByFuncionario(funcionario)).thenReturn(carrinhos);
        List<Carrinho> result = carrinhoService.listarCarrinhosPorFuncionario(funcionario);
        assertEquals(carrinhos, result);
    }

    @Test
    public void buscarCarrinhoPorFuncionarioEId_ExistenteTest() {
        Long id = 1L;
        Funcionario funcionario = new Funcionario();
        Carrinho carrinho = new Carrinho(id, funcionario, List.of(new ItemCarrinho()));
        when(carrinhoRepository.findByFuncionarioAndId(funcionario, id)).thenReturn(Optional.of(carrinho));

        Optional<Carrinho> result = carrinhoService.buscarCarrinhoPorFuncionarioEId(funcionario, id);
        assertTrue(result.isPresent());
        assertEquals(carrinho, result.get());
    }

    @Test
    public void buscarCarrinhoPorFuncionarioEId_NaoExistenteTest() {
        Long id = 1L;
        Funcionario funcionario = new Funcionario();
        when(carrinhoRepository.findByFuncionarioAndId(funcionario, id)).thenReturn(Optional.empty());

        Optional<Carrinho> result = carrinhoService.buscarCarrinhoPorFuncionarioEId(funcionario, id);
        assertTrue(result.isEmpty());
    }
}
