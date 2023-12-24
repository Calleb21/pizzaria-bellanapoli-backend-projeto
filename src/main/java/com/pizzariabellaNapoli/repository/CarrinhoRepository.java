package com.pizzariabellaNapoli.repository;

import com.pizzariabellaNapoli.domain.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Description of CarrinhoRepository
 * Created by calle on 20/12/2023.
 */
@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {


}
