package com.example.project.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.project.model.Driver;

import com.example.project.repository.DriverRepo;

@RestController
@RequestMapping("/driver")
@CrossOrigin("http://localhost:3000")

public class DriverApi {

    @Autowired

    public DriverRepo driverRepo;

   @PostMapping("/ingiza")
   public ResponseEntity<?>adddriver(@RequestBody Driver driver){
        try{
            Driver driver2 = driverRepo.save(driver);
            return new ResponseEntity<>("imefanikiwa kuingia",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("haijafanikiwa kuingia",HttpStatus.NOT_MODIFIED);
        }
   }



    @GetMapping("/alldrivers")

    public ResponseEntity<?>alldrivers(){
        try{
            List<Driver>driverList = driverRepo.findAll();
            if(driverList.isEmpty()){
                return new ResponseEntity<>("no data found",HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(driverList,HttpStatus.OK);
            }
        }catch(Exception e){
            return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/futa/{driverId}")
    public ResponseEntity<?>deletedata(@PathVariable int driverId){
        try{
            driverRepo.deleteById(driverId);
            return new ResponseEntity<>("umefanikiwa kufuta",HttpStatus.OK);

        }catch(Exception e){
            return new ResponseEntity<>("hamna data ya kufuta", HttpStatus.CONFLICT);
        }
    }


    @GetMapping("/byID/{id}")
    public ResponseEntity<?>getbyId(@PathVariable int id){
        try{
            Optional<Driver>optionaldriver = driverRepo.findById(id);
            if (optionaldriver.isPresent()){
                return new ResponseEntity<>(optionaldriver,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("hamna data",HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            return new ResponseEntity<>("haipatikani", HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/badili/{id}")
    public ResponseEntity<?>changedriv(@PathVariable int id,@RequestBody Driver driver){
        try{
            if (driverRepo.findById(id).isPresent()){

                Driver driver2 = driverRepo.save(driver);

                driver2.setFirstName(driver.getFirstName());
                driver2.setLastName(driver.getLastName());
                driver2.setMobileNo(driver.getMobileNo());
                
                Driver changedriv = driverRepo.save(driver2);
                return new ResponseEntity<>("imebadilika",HttpStatus.OK);
            }else{
                return new ResponseEntity<>("not updated",HttpStatus.CONFLICT);
            }
        }catch(Exception e){
            return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
        }
    }
}
