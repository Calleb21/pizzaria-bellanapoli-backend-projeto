package com.pizzariabellaNapoli.repository;

import com.pizzariabellaNapoli.domain.Carrinho;
import com.pizzariabellaNapoli.domain.CupomFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Description of CupomFiscalRepository
 * Created by calle on 20/12/2023.
 */
@Repository
public interface CupomFiscalRepository extends JpaRepository<CupomFiscal, Long> {

    List<CupomFiscal> findByCarrinho(Carrinho carrinho);

    Optional<CupomFiscal> findByCarrinhoAndId(Carrinho carrinho, Long id);
}
