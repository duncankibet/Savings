package com.presta.savings.service;

import com.presta.savings.dao.GeneralResponse;
import com.presta.savings.dao.SaveProductRequest;
import com.presta.savings.dao.SaveTransactionRequest;
import com.presta.savings.dao.SavingRequest;
import com.presta.savings.model.SavingProduct;
import com.presta.savings.model.Transaction;
import com.presta.savings.repository.CustomerRepository;
import com.presta.savings.repository.SavingProductRepository;
import com.presta.savings.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    SavingProductRepository savingProductRepository;
    @Autowired
    SavingService savingService;
    GeneralResponse generalResponse;

    public ResponseEntity<?> addTransaction(SaveTransactionRequest saveTransactionRequest) {
        generalResponse = new GeneralResponse();

        try {
            if(savingProductRepository.existsById(saveTransactionRequest.getProductId())){
                if(customerRepository.existsById(saveTransactionRequest.getCustomerId())){
                Transaction transaction=setTransaction(saveTransactionRequest);
                transactionRepository.save(transaction);

                generalResponse.setStatus(HttpStatus.CREATED);
                generalResponse.setDescription("Transaction added successfully");

                return new ResponseEntity<>(generalResponse,HttpStatus.CREATED);}
            }else{
                generalResponse.setStatus(HttpStatus.BAD_REQUEST);
                generalResponse.setDescription("Customer or Product with that id does not exist");

                return new ResponseEntity<>(generalResponse,HttpStatus.BAD_REQUEST);

            }


            return new ResponseEntity<>(generalResponse,HttpStatus.CREATED);



        } catch (Exception e) {
            generalResponse.setStatus(HttpStatus.BAD_REQUEST);
            generalResponse.setDescription("Something went wrong, please check your request and try again");

            return new ResponseEntity<>(generalResponse,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> updateTransaction(SaveTransactionRequest saveTransactionRequest, long id) {
        generalResponse = new GeneralResponse();

        if(transactionRepository.existsById(id)){
            if(savingProductRepository.existsById(saveTransactionRequest.getProductId())){
                if(customerRepository.existsById(saveTransactionRequest.getCustomerId())) {
                    Transaction transaction = setTransaction(saveTransactionRequest);
                    transaction.setId(id);

                    transactionRepository.save(transaction);
                    generalResponse.setStatus(HttpStatus.CREATED);
                    generalResponse.setDescription("Transaction details updated successfully");

                    return new ResponseEntity<>(generalResponse, HttpStatus.CREATED);

                }}

        }
            generalResponse.setStatus(HttpStatus.BAD_REQUEST);
            generalResponse.setDescription("Transaction with provided id does not exist");

            return new ResponseEntity<>(generalResponse,HttpStatus.BAD_REQUEST);

    }

    public Transaction setTransaction(SaveTransactionRequest saveTransactionRequest){
        Transaction  transaction= new Transaction();
        transaction.setTransactionDate(LocalDateTime.now().toString());
        transaction.setTransactionRef(saveTransactionRequest.getTransactionRef());
        transaction.setAmount(saveTransactionRequest.getAmount());
        transaction.setPaymentChannel(saveTransactionRequest.getPaymentChannel());
        transaction.setCustomer(customerRepository.findById(saveTransactionRequest.getCustomerId()).get());
        transaction.setSavingProduct(savingProductRepository.findById(saveTransactionRequest.getProductId()).get());


        SavingRequest savingRequest= new SavingRequest();
        savingRequest.setTransactionAmount(saveTransactionRequest.getAmount());
        savingRequest.setCustomerId(saveTransactionRequest.getCustomerId());
        savingRequest.setProductId(saveTransactionRequest.getProductId());

        savingService.addOrUpdateSavings(savingRequest);


        return transaction;

    }




    public ResponseEntity<?> findById(Long id) {
        generalResponse = new GeneralResponse();
        try {

            return new ResponseEntity<>(transactionRepository.findById(id).get(),HttpStatus.FOUND);

        } catch (Exception e) {
            generalResponse.setStatus(HttpStatus.NOT_FOUND);
            generalResponse.setDescription("Transaction with that id does not exist");
            return new ResponseEntity<>(generalResponse,HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<?> findAll() {

        List<Transaction> transactions = transactionRepository.findAll();


        return new ResponseEntity<>(transactions,HttpStatus.FOUND);

    }
    public ResponseEntity<?> deleteById(Long id) {
        generalResponse = new GeneralResponse();
        if(transactionRepository.existsById(id)){
            transactionRepository.deleteById(id);
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
