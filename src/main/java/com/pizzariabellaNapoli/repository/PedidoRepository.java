package com.pizzariabellaNapoli.repository;

import com.pizzariabellaNapoli.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Description of PedidoRepository
 * Created by calle on 18/12/2023.
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
