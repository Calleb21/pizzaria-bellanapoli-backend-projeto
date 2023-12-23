package com.pizzariabellaNapoli.service;

import com.pizzariabellaNapoli.domain.Funcionario;
import com.pizzariabellaNapoli.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Description of FuncionarioService
 * Created by calle on 18/12/2023.
 */
@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> listarTodosFuncionarios() {
        return funcionarioRepository.findAll();
    }


    public Funcionario salvarFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }


    public Funcionario buscarFuncionarioPorEmail(String email) {
        return funcionarioRepository.findByEmail(email)
                .orElse(null);
    }

    public Optional<Funcionario> autenticarFuncionario(String email, String senha) {
        Optional<Funcionario> funcionario = funcionarioRepository.findByEmail(email);

        if (funcionario.isPresent() && funcionario.get().getSenha().equals(senha)) {
            return funcionario;
        } else {
            return Optional.empty();
        }
    }
}
