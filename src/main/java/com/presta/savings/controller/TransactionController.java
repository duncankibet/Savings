package com.presta.savings.controller;

import com.presta.savings.dao.SaveProductRequest;
import com.presta.savings.dao.SaveTransactionRequest;
import com.presta.savings.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/transaction/")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @PostMapping("add")
    public ResponseEntity<?> addTransaction(@RequestBody SaveTransactionRequest saveTransactionRequest){
        return transactionService.addTransaction(saveTransactionRequest);

    }
    @PutMapping("update/{id}")
    public ResponseEntity<?> updateTransaction(@RequestBody SaveTransactionRequest saveTransactionRequest, @PathVariable long id){
        return transactionService.updateTransaction(saveTransactionRequest,id);

    }
    @GetMapping("list")
    public ResponseEntity<?> listAll(){
        return transactionService.findAll();

    }
    @GetMapping("findById/{id}")
    public ResponseEntity<?> findById(@PathVariable long id){
        return transactionService.findById(id);

    }
    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id){
        return transactionService.deleteById(id);

    }
}
