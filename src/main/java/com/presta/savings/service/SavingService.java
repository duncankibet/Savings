package com.presta.savings.service;

import com.presta.savings.dao.GeneralResponse;
import com.presta.savings.dao.SaveProductRequest;
import com.presta.savings.dao.SavingRequest;
import com.presta.savings.dao.SavingResponse;
import com.presta.savings.model.Customer;
import com.presta.savings.model.Saving;
import com.presta.savings.model.SavingProduct;
import com.presta.savings.model.Transaction;
import com.presta.savings.repository.CustomerRepository;
import com.presta.savings.repository.SavingProductRepository;
import com.presta.savings.repository.SavingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SavingService {
    @Autowired
    SavingRepository savingRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    SavingProductRepository savingProductRepository;
    GeneralResponse generalResponse;


    public ResponseEntity<?> addOrUpdateSavings(SavingRequest savingRequest) {
        generalResponse = new GeneralResponse();

        try {
            setSaving(savingRequest);

            generalResponse.setStatus(HttpStatus.CREATED);
            generalResponse.setDescription("Saving  added successfully");

            return new ResponseEntity<>(generalResponse,HttpStatus.CREATED);



        } catch (Exception e) {
            generalResponse.setStatus(HttpStatus.BAD_REQUEST);
            generalResponse.setDescription("Something went wrong, please check your request and try again");

            return new ResponseEntity<>(generalResponse,HttpStatus.BAD_REQUEST);
        }
    }


    public void setSaving(SavingRequest savingRequest){

         SavingProduct savingProduct=savingProductRepository.findById(savingRequest.getProductId()).get();
        Customer customer=customerRepository.findById(savingRequest.getCustomerId()).get();
          System.out.println(savingRepository.findByCustomerId(savingRequest.getCustomerId())>0);
        System.out.println(savingRepository.findByProductId(savingRequest.getProductId())>0);
        if(savingRepository.findByCustomerId(savingRequest.getCustomerId())>0){
            if(savingRepository.findByProductId(savingRequest.getProductId())>0) {

                Saving saving = savingRepository.findByCustomerAndSavingProduct(savingRequest.getProductId(),savingRequest.getCustomerId()).get();

                saving.setTotalAmount(saving.getTotalAmount() + savingRequest.getTransactionAmount());
                savingRepository.save(saving);
                return;
            }
        }
            Saving saving = new Saving();

            saving.setSavingProduct(savingProduct);
            saving.setCustomer(customer);
            saving.setTotalAmount(savingRequest.getTransactionAmount());

            savingRepository.save(saving);

            return;


    }


    public ResponseEntity<?> findById(Long id) {
        generalResponse = new GeneralResponse();
        try {
         Saving saving= savingRepository.findById(id).get();
            SavingResponse savingResponse=new SavingResponse();
            savingResponse.setTotalAmount(saving.getTotalAmount());
            savingResponse.setSavingProductName(saving.getSavingProduct().getProductName());
            savingResponse.setCustomerName(saving.getCustomer().getName());
            return new ResponseEntity<>(savingResponse,HttpStatus.FOUND);

        } catch (Exception e) {
            generalResponse.setStatus(HttpStatus.NOT_FOUND);
            generalResponse.setDescription("Saving with that id does not exist");
            return new ResponseEntity<>(generalResponse,HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<?> findAll() {

        List<Saving> transactions = savingRepository.findAll();
        List<SavingResponse> savingResponseList= new ArrayList<>();
        transactions.stream().forEach(saving->{
            SavingResponse savingResponse=new SavingResponse();
            savingResponse.setTotalAmount(saving.getTotalAmount());
            savingResponse.setSavingProductName(saving.getSavingProduct().getProductName());
            savingResponse.setCustomerName(saving.getCustomer().getName());
            savingResponseList.add(savingResponse);
        });


        return new ResponseEntity<>(savingResponseList,HttpStatus.FOUND);

    }

}
