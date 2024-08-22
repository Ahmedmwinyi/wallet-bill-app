package com.janu.wallet_bill_app.controller;

import com.janu.wallet_bill_app.exceptions.AdminException;
import com.janu.wallet_bill_app.exceptions.CustomerException;
import com.janu.wallet_bill_app.exceptions.LoginException;
import com.janu.wallet_bill_app.model.Customer;
import com.janu.wallet_bill_app.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) throws CustomerException {
        Customer added_customer = customerService.addCustomer(customer);

        return new ResponseEntity<Customer>(added_customer, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomerHandler(@RequestParam String key, @Valid @RequestBody Customer customer) throws CustomerException, LoginException {

        Customer updated_customer = customerService.updateCustomer(key, customer);
        return new ResponseEntity<Customer>(updated_customer, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> removeCustomerHandler(@RequestParam String key, @Valid @RequestParam String mobileNumber) throws CustomerException, LoginException {

        String result = customerService.removeCustomer(key, mobileNumber);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    @GetMapping("/view")
    public ResponseEntity<Customer> viewCustomerHandler(@RequestParam String key, @Valid @RequestParam String mobileNumber) throws CustomerException, LoginException {
        Customer customer = customerService.viewCustomer(key, mobileNumber);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @GetMapping("/view-all")
    public ResponseEntity<List<Customer>> viewAllCustomers(@RequestParam String key) {
        try {
            List<Customer> customers = customerService.viewAllCustomers(key);
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (AdminException | LoginException | CustomerException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/view-mobile")
    public ResponseEntity<Customer> viewCustomerByMobileNumber(@RequestParam String mobileNumber) {
        try {
            Customer customer = customerService.viewCustomerByMobileNumber(mobileNumber);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (CustomerException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/admin/remove")
    public ResponseEntity<String> adminRemoveCustomer(@RequestParam String key, @RequestParam String mobileNumber) {
        try {
            String response = customerService.adminRemoveCustomer(key, mobileNumber);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (AdminException | LoginException | CustomerException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
