package com.example.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.model.Payment;
import com.example.project.repository.PaymentRepo;

@RestController
@RequestMapping("/payment")

public class PaymentApi {

    @Autowired

    public PaymentRepo paymentRepo;

    @PostMapping("/add")

    public ResponseEntity<?>insert(@RequestBody Payment payment){
        try{
            Payment payment2 = paymentRepo.save(payment);
            return new ResponseEntity<>("Imefanikiwa kuingia",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("haijaingia",HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/allpayments")
    public ResponseEntity<?>allpayments(){
        try{
            List<Payment>paymentList = paymentRepo.findAll();
            if(paymentList.isEmpty()){
                return new ResponseEntity<>("no data found",HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity<>(paymentList,HttpStatus.OK);
            }
        }catch(Exception e){
            return new ResponseEntity<>("error",HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/getbyID/{id}")
    public ResponseEntity<?>paymentById(@PathVariable int id){
        try{
            Optional<Payment>optionalpayment = paymentRepo.findById(id);
            if (optionalpayment.isEmpty()){
                return new ResponseEntity<>("hamna data",HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(optionalpayment,HttpStatus.OK);
            }
        }catch(Exception e){
            return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable int id){
        try{
            paymentRepo.deleteById(id);
            return new ResponseEntity<>("deleted",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("not found",HttpStatus.CONFLICT);
        }
    }


    @PutMapping("/update/{ID}")
    public ResponseEntity<?>update(@PathVariable int ID, @RequestBody Payment payment){
        try{
            if(paymentRepo.findById(ID).isPresent()){
                Payment payment2 = paymentRepo.save(payment);

                payment2.setFeesAmount(payment.getFeesAmount());
                payment2.setFeesDescr(payment.getFeesDescr());

                Payment update = paymentRepo.save(payment2);
                return new ResponseEntity<>("updated",HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Not updated",HttpStatus.CONFLICT);
            }
        }catch(Exception e){
            return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
        }
    }
    
}
