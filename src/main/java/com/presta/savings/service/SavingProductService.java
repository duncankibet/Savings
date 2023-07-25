package com.presta.savings.service;

import com.presta.savings.dao.GeneralResponse;
import com.presta.savings.dao.SaveProductRequest;
import com.presta.savings.model.SavingProduct;
import com.presta.savings.repository.SavingProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavingProductService {
    @Autowired
    SavingProductRepository savingProductRepository;
    GeneralResponse generalResponse;

    public ResponseEntity<?> addSavingProduct(SaveProductRequest saveProductRequest) {
        generalResponse = new GeneralResponse();

        try {
            SavingProduct product=setProduct(saveProductRequest);
            savingProductRepository.save(product);
            generalResponse.setStatus(HttpStatus.CREATED);
            generalResponse.setDescription("Saving Product added successfully");

            return new ResponseEntity<>(generalResponse,HttpStatus.CREATED);



        } catch (Exception e) {
            generalResponse.setStatus(HttpStatus.BAD_REQUEST);
            generalResponse.setDescription("Something went wrong, please check your request and try again");

            return new ResponseEntity<>(generalResponse,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> updateSavingProduct(SaveProductRequest saveProductRequest, long id) {
        generalResponse = new GeneralResponse();

                if(savingProductRepository.existsById(id)){
            SavingProduct product= setProduct(saveProductRequest);
            product.setId(id);

            savingProductRepository.save(product);
            generalResponse.setStatus(HttpStatus.CREATED);
            generalResponse.setDescription("Saving product details updated successfully");

            return new ResponseEntity<>(generalResponse,HttpStatus.CREATED);



        }else{
            generalResponse.setStatus(HttpStatus.BAD_REQUEST);
            generalResponse.setDescription("Saving Product with provided id does not exist");

            return new ResponseEntity<>(generalResponse,HttpStatus.BAD_REQUEST);
        }
    }

    public SavingProduct setProduct(SaveProductRequest saveProductRequest){
        SavingProduct  product= new SavingProduct();

        product.setProductName(saveProductRequest.getName());


        return product;

    }



    public ResponseEntity<?> findById(Long id) {
        generalResponse = new GeneralResponse();
        try {

            return new ResponseEntity<>(savingProductRepository.findById(id).get(),HttpStatus.FOUND);

        } catch (Exception e) {
            generalResponse.setStatus(HttpStatus.NOT_FOUND);
            generalResponse.setDescription("Product with that id does not exist");
            return new ResponseEntity<>(generalResponse,HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<?> findAll() {

        List<SavingProduct> customerList = savingProductRepository.findAll();


        return new ResponseEntity<>(customerList,HttpStatus.FOUND);

    }
    public ResponseEntity<?> deleteById(Long id) {
        generalResponse = new GeneralResponse();
        if(savingProductRepository.existsById(id)){
            savingProductRepository.deleteById(id);
            generalResponse.setStatus(HttpStatus.ACCEPTED);
            generalResponse.setDescription("Product Deleted Successfully");
            return new ResponseEntity<>(generalResponse,HttpStatus.ACCEPTED);
        }else{
            generalResponse.setStatus(HttpStatus.NOT_FOUND);
            generalResponse.setDescription("Product with that id does not exist");
            return new ResponseEntity<>(generalResponse,HttpStatus.NOT_FOUND);
        }






    }
}
