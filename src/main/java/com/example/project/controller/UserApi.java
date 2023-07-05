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


import com.example.project.model.UserRegistration;
import com.example.project.repository.UserRepo;

@RestController
@RequestMapping("/Api/v1")
@CrossOrigin("http://localhost:3000")
public class UserApi {

    @Autowired

    public UserRepo userRepo;

    @GetMapping("/getAll")
    public ResponseEntity<?> allUsers(){
        try{
            List<UserRegistration>userRegistrationList = userRepo.findAll();
            if(userRegistrationList.isEmpty()){
                return new ResponseEntity<>("no users",HttpStatus.CONFLICT);
            }else{
                return new ResponseEntity<>(userRegistrationList,HttpStatus.ACCEPTED);
            }
        }catch(Exception e){
            return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/addUser")
    public ResponseEntity<?>add(@RequestBody UserRegistration userRegistration){
        try{
            UserRegistration userRegistration2 = userRepo.save(userRegistration);
            return new ResponseEntity<>("inserted successfully",HttpStatus.ACCEPTED);
        }catch(Exception e){
            return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/delete/{id}")
      public ResponseEntity<?>deletedata(@PathVariable int id){
        try{
            userRepo.deleteById(id);
            return new ResponseEntity<>("deleted",HttpStatus.OK);

        }catch(Exception e){
            return new ResponseEntity<>("hamna data ya kufuta", HttpStatus.CONFLICT);
        }
    }




     @GetMapping("/userbyID/{id}")
    public ResponseEntity<?>getbyId(@PathVariable int id){
        try{
            Optional<UserRegistration>optionaluserRegistration = userRepo.findById(id);
            if (optionaluserRegistration.isPresent()){
                return new ResponseEntity<>(optionaluserRegistration,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("hamna data",HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            return new ResponseEntity<>("haipatikani", HttpStatus.CONFLICT);
        }
    }


        @PutMapping("/update/{id}")
    public ResponseEntity<?>changeuser(@PathVariable int id,@RequestBody UserRegistration userRegistration){
        try{
            if (userRepo.findById(id).isPresent()){

                UserRegistration usRegistration = userRepo.save(userRegistration);

                usRegistration.setUserName(userRegistration.getUserName());
                usRegistration.setPassword(userRegistration.getPassword());
               

                UserRegistration changeuser = userRepo.save(usRegistration);
             
                return new ResponseEntity<>("updated",HttpStatus.OK);
            }else{
                return new ResponseEntity<>("not updated",HttpStatus.CONFLICT);
            }
        }catch(Exception e){
            return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
        }
    }
    
}

