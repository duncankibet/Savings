package com.presta.savings.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveCustomerRequest {
    private String name;
    private String nationalIdNo;
    private String memberNo;
    private String phoneNo;

    private String  email;
}
