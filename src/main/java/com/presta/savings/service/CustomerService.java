package com.presta.savings.service;

import com.presta.savings.dao.GeneralResponse;
import com.presta.savings.dao.SaveCustomerRequest;
import com.presta.savings.model.Customer;
import com.presta.savings.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    GeneralResponse generalResponse;

        public ResponseEntity<?> addCustomer(SaveCustomerRequest saveCustomerRequest) {
            generalResponse = new GeneralResponse();

            try {
                Customer  customer=setCustomer(saveCustomerRequest);
                customerRepository.save(customer);
                generalResponse.setStatus(HttpStatus.CREATED);
                generalResponse.setDescription("Customer added successfully");

                return new ResponseEntity<>(generalResponse,HttpStatus.CREATED);



            } catch (Exception e) {
                generalResponse.setStatus(HttpStatus.BAD_REQUEST);
                generalResponse.setDescription("Something went wrong, please check your request and try again");

                return new ResponseEntity<>(generalResponse,HttpStatus.BAD_REQUEST);
            }
        }

    public ResponseEntity<?> updateCustomer(SaveCustomerRequest saveCustomerRequest, long id) {
        generalResponse = new GeneralResponse();

        if(customerRepository.existsById(id)){
           Customer customer= setCustomer(saveCustomerRequest);
           customer.setId(id);

            customerRepository.save(customer);
            generalResponse.setStatus(HttpStatus.CREATED);
            generalResponse.setDescription("Customer details updated successfully");

            return new ResponseEntity<>(generalResponse,HttpStatus.CREATED);



        }else{
            generalResponse.setStatus(HttpStatus.BAD_REQUEST);
            generalResponse.setDescription("Customer with provided id does not exist");

            return new ResponseEntity<>(generalResponse,HttpStatus.BAD_REQUEST);
        }
    }

    public Customer setCustomer(SaveCustomerRequest saveCustomerRequest){
        Customer  customer= new Customer();

        customer.setName(saveCustomerRequest.getName());
        customer.setEmail(saveCustomerRequest.getEmail());
        customer.setPhoneNo(saveCustomerRequest.getPhoneNo());
        customer.setMemberNo(saveCustomerRequest.getMemberNo());
        customer.setNationalIdNo(saveCustomerRequest.getNationalIdNo());

        return customer;

    }



        public ResponseEntity<?> findById(Long id) {
            generalResponse = new GeneralResponse();
            try {

                return new ResponseEntity<>(customerRepository.findById(id).get(),HttpStatus.FOUND);

            } catch (Exception e) {
                generalResponse.setStatus(HttpStatus.NOT_FOUND);
                generalResponse.setDescription("Customer with that id does not exist");
                return new ResponseEntity<>(generalResponse,HttpStatus.NOT_FOUND);
            }

        }

        public ResponseEntity<?> findAll() {

            List<Customer> customerList = customerRepository.findAll();


            return new ResponseEntity<>(customerList,HttpStatus.FOUND);

        }
    public ResponseEntity<?> deleteById(Long id) {
        generalResponse = new GeneralResponse();
        if(customerRepository.existsById(id)){
            customerRepository.deleteById(id);
            generalResponse.setStatus(HttpStatus.ACCEPTED);
            generalResponse.setDescription("Customer Deleted Successfully");
            return new ResponseEntity<>(generalResponse,HttpStatus.ACCEPTED);
        }else{
            generalResponse.setStatus(HttpStatus.NOT_FOUND);
            generalResponse.setDescription("Customer with that id does not exist");
            return new ResponseEntity<>(generalResponse,HttpStatus.NOT_FOUND);
        }






    }




}


