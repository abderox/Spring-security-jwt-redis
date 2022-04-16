package com.expressionbesoins.restexpbesoin.controller;


import com.expressionbesoins.restexpbesoin.dto.UserLoginDTO;
import com.expressionbesoins.restexpbesoin.model.Role;
import com.expressionbesoins.restexpbesoin.model.User;
import com.expressionbesoins.restexpbesoin.repository.RoleRepo;
import com.expressionbesoins.restexpbesoin.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;


@RequestMapping(value ="/Register")
@RestController
public class UserController {
@Autowired
UserRepo roleRepo;

    @PostMapping("/Role")
    public ResponseEntity<User> save(@RequestBody User customer) {
        try {
            return new ResponseEntity<>(roleRepo.save(customer), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//    @GetMapping("/customers/{id:\\d+}")
//    public ResponseEntity<Customer> getSingleCustomer(@PathVariable Long id) {
//        Optional<Customer> customer = customerRepo.findById(id);
//        if (customer.isPresent()) {
//            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

}
