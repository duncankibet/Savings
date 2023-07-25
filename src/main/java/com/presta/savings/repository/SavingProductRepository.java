package com.presta.savings.repository;

import com.presta.savings.model.SavingProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingProductRepository extends JpaRepository<SavingProduct,Long> {
}
