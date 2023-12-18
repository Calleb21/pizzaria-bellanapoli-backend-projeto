package com.pizzariabellaNapoli.repository;

import com.pizzariabellaNapoli.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Description of FuncionarioRepository
 * Created by calle on 18/12/2023.
 */
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByEmail(String email);
}
