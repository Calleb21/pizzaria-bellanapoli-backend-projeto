package com.pizzariabellaNapoli.repository;

import com.pizzariabellaNapoli.domain.Carrinho;
import com.pizzariabellaNapoli.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Description of CarrinhoRepository
 * Created by calle on 20/12/2023.
 */
@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

    List<Carrinho> findByFuncionario(Funcionario funcionario);

    Optional<Carrinho> findByFuncionarioAndId(Funcionario funcionario, Long id);
}
