package com.pizzariabellaNapoli.repository;

import com.pizzariabellaNapoli.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Description of ItemPedidoRepository
 * Created by calle on 18/12/2023.
 */
@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
