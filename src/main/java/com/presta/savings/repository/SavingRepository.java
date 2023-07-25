package com.presta.savings.repository;

import com.presta.savings.model.Customer;
import com.presta.savings.model.Saving;
import com.presta.savings.model.SavingProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SavingRepository extends JpaRepository<Saving,Long> {
    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM savings where customer_id=:customer_id")
    int findByCustomerId(@Param("customer_id") long customer_id);
    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM savings where product_id=:product_id")
    int findByProductId(@Param("product_id") long product_id);

    @Query(nativeQuery = true, value = "SELECT * FROM savings where product_id=:product_id and customer_id=:customer_id" )
    Optional<Saving>findByCustomerAndSavingProduct(@Param("product_id") long product_id, @Param("customer_id") long customer_id);
}
