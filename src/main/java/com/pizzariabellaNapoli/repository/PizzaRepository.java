package com.pizzariabellaNapoli.repository;

import com.pizzariabellaNapoli.domain.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Description of PizzaRepository
 * Created by calle on 18/12/2023.
 */
@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
