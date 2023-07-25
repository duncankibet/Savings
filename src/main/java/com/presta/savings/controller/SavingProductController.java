package com.presta.savings.controller;

import com.presta.savings.dao.SaveCustomerRequest;
import com.presta.savings.dao.SaveProductRequest;
import com.presta.savings.service.SavingProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/product/")
public class SavingProductController {
    @Autowired
    SavingProductService savingProductService;

    @PostMapping("add")
    public ResponseEntity<?> addProduct(@RequestBody SaveProductRequest saveProductRequest){
        return savingProductService.addSavingProduct(saveProductRequest);

    }
    @PutMapping("update/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody SaveProductRequest saveProductRequest, @PathVariable long id){
        return savingProductService.updateSavingProduct(saveProductRequest,id);

    }
    @GetMapping("list")
    public ResponseEntity<?> listAll(){
        return savingProductService.findAll();

    }
    @GetMapping("findById/{id}")
    public ResponseEntity<?> findById(@PathVariable long id){
        return savingProductService.findById(id);

    }
    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id){
        return savingProductService.deleteById(id);

    }

}
