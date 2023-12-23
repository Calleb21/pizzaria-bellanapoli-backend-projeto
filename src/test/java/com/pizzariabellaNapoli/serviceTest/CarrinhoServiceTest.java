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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

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

        for (int i = 0; i < carrinho.getItens().size(); i++) {
            ItemCarrinho originalItem = carrinho.getItens().get(i);
            ItemCarrinho resultItem = result.getItens().get(i);

            assertEquals(originalItem.getId(), resultItem.getId());
            assertEquals(originalItem.getQuantidade(), resultItem.getQuantidade());
            assertEquals(originalItem.getPizza(), resultItem.getPizza());
            assertEquals(originalItem.getCarrinho(), resultItem.getCarrinho());
        }
    }
}