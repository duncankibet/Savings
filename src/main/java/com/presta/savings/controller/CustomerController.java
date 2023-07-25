package com.presta.savings.controller;

import com.presta.savings.dao.SaveCustomerRequest;
import com.presta.savings.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/customer/")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("add")
    public ResponseEntity<?> addCustomer(@RequestBody SaveCustomerRequest saveCustomerRequest){
           return customerService.addCustomer(saveCustomerRequest);

    }
    @PutMapping("update/{id}")
    public ResponseEntity<?> addCustomer(@RequestBody SaveCustomerRequest saveCustomerRequest, @PathVariable long id){
        return customerService.updateCustomer(saveCustomerRequest,id);

    }
    @GetMapping("list")
    public ResponseEntity<?> listAll(){
        return customerService.findAll();

    }
    @GetMapping("findById/{id}")
    public ResponseEntity<?> findById(@PathVariable long id){
        return customerService.findById(id);

    }
    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id){
        return customerService.deleteById(id);

    }


}
