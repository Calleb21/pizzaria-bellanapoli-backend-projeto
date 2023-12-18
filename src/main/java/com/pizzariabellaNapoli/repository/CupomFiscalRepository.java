package com.pizzariabellaNapoli.repository;

import com.pizzariabellaNapoli.domain.CupomFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Description of CupomFiscalRepository
 * Created by calle on 18/12/2023.
 */
@Repository
public interface CupomFiscalRepository extends JpaRepository<CupomFiscal, Long> {
}
