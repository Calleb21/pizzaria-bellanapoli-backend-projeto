package com.pizzariabellaNapoli.repository;

import com.pizzariabellaNapoli.domain.ItemCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Description of ItemCarrinhoRepository
 * Created by calle on 20/12/2023.
 */
@Repository
public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Long> {

}
