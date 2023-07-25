package com.presta.savings.controller;

import com.presta.savings.service.SavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/saving/")
public class SavingController {
    @Autowired
    SavingService savingService;

    @GetMapping("list")
    public ResponseEntity<?> listAllCustomerSavings(){
        return savingService.findAll();

    }
    @GetMapping("findById/{id}")
    public ResponseEntity<?> findByID(@PathVariable long id){
        return savingService.findById(id);

    }

}
