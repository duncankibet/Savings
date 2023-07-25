package com.presta.savings.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.presta.savings.model.Customer;
import com.presta.savings.model.SavingProduct;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SavingRequest {
    private double transactionAmount;
    private long productId;
    private long customerId;
}
