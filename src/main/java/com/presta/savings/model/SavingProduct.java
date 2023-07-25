package com.presta.savings.model;




import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.NotBlank;


@Entity
@Table(name="saving_products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SavingProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String productName;



}