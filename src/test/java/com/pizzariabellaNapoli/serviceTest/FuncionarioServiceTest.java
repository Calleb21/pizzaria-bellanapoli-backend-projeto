package com.pizzariabellaNapoli.serviceTest;

import com.pizzariabellaNapoli.domain.Funcionario;
import com.pizzariabellaNapoli.repository.FuncionarioRepository;
import com.pizzariabellaNapoli.service.FuncionarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Description of FuncionarioServiceTest
 * Created by calle on 18/12/2023.
 */
public class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private FuncionarioService funcionarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void listarTodosFuncionariosTest() {
        List<Funcionario> funcionarios = Arrays.asList(
                new Funcionario(1L, "Calleb", "calleb@email.com", "calleb123"),
                new Funcionario(2L, "Camargo", "camargo@email.com", "camargo123")
        );

        when(funcionarioRepository.findAll()).thenReturn(funcionarios);
        List<Funcionario> result = funcionarioService.listarTodosFuncionarios();
        assertEquals(funcionarios, result);
    }

    @Test
    public void buscarFuncionariosPorId_ExistenteTest() {
        Long id = 1L;
        Funcionario funcionario = new Funcionario(id, "Calleb", "calleb@email.com", "calleb123");
        when(funcionarioRepository.findById(id)).thenReturn(Optional.of(funcionario));

        Optional<Funcionario> result = funcionarioService.buscarFuncionarioPorId(id);
        assertTrue(result.isPresent());
        assertEquals(funcionario, result.get());
    }

    @Test
    public void buscarFuncionariosPorId_NaoExistenteTest() {
        Long id = 1L;
        when(funcionarioRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Funcionario> result = funcionarioService.buscarFuncionarioPorId(id);
        assertTrue(result.isEmpty());
    }

    @Test
    public void salvarFuncionarioTest() {
        Funcionario funcionario = new Funcionario(null, "Calleb", "calleb@email.com", "calleb123");
        Funcionario funcionario1 = new Funcionario(1L, "Calleb", "calleb@email.com", "calleb123");
        when(funcionarioRepository.save(funcionario)).thenReturn(funcionario1);

        Funcionario result = funcionarioService.salvarFuncionario(funcionario);
        assertNotNull(result.getId());
        assertEquals(funcionario1.getNome(), result.getNome());
        assertEquals(funcionario1.getEmail(), result.getEmail());
        assertEquals(funcionario1.getSenha(), result.getSenha());
    }

    @Test
    public void excluirFuncionarioTest() {
        Long id = 1L;
        funcionarioService.excluirFuncionario(id);
        verify(funcionarioRepository, times(1)).deleteById(id);
    }
}
