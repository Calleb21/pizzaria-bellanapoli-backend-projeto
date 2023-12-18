package com.pizzariabellaNapoli.serviceTest;

import com.pizzariabellaNapoli.domain.Pizza;
import com.pizzariabellaNapoli.repository.PizzaRepository;
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
 * Description of PizzaServiceTest
 * Created by calle on 18/12/2023.
 */
public class PizzaServiceTest {

    @Mock
    private PizzaRepository pizzaRepository;

    @InjectMocks
    private PizzaService pizzaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void listarTodasPizzasTest() {
        List<Pizza> pizzas = Arrays.asList(
                new Pizza(1L, "img.png","Margherita", "Tomate, mussarela, manjericão", BigDecimal.valueOf(25.0)),
                new Pizza(2L, "img.png","Calabresa", "Calabresa, cebola, mussarela", BigDecimal.valueOf(28.0))
        );
        when(pizzaRepository.findAll()).thenReturn(pizzas);

        List<Pizza> result = pizzaService.listarTodasPizzas();
        assertEquals(pizzas, result);
    }

    @Test
    void buscarPizzaPorId_Existente() {
        Long id = 1L;
        Pizza pizza = new Pizza(id, "","Margherita", "Tomate, mussarela, manjericão", BigDecimal.valueOf(25.0));
        when(pizzaRepository.findById(id)).thenReturn(Optional.of(pizza));

        Optional<Pizza> result = pizzaService.buscarPizzaPorId(id);
        assertTrue(result.isPresent());
        assertEquals(pizza, result.get());
    }

    @Test
    void buscarPizzaPorId_NaoExistente() {
        Long id = 1L;
        when(pizzaRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Pizza> result = pizzaService.buscarPizzaPorId(id);
        assertTrue(result.isEmpty());
    }

    @Test
    void salvarPizza() {
        Pizza novaPizza = new Pizza(null, "img.png","Quatro Queijos", "Mussarela, parmesão, provolone, gorgonzola", BigDecimal.valueOf(30.0));
        Pizza pizzaSalva = new Pizza(1L, "img.png","Quatro Queijos", "Mussarela, parmesão, provolone, gorgonzola", BigDecimal.valueOf(30.0));
        when(pizzaRepository.save(novaPizza)).thenReturn(pizzaSalva);

        Pizza result = pizzaService.salvarPizza(novaPizza);
        assertNotNull(result.getId());
        assertEquals(pizzaSalva.getNome(), result.getNome());
        assertEquals(pizzaSalva.getIngredientes(), result.getIngredientes());
        assertEquals(pizzaSalva.getValor(), result.getValor());
    }

    @Test
    void excluirPizza() {
        Long id = 1L;
        pizzaService.excluirPizza(id);
        verify(pizzaRepository, times(1)).deleteById(id);
    }
}
