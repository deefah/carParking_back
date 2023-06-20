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

import com.example.project.model.Reservation;
import com.example.project.repository.ReservationRepo;

@RestController
@RequestMapping("/reserve")

public class ReservationApi {

    @Autowired

    public ReservationRepo reservationRepo;

    @PostMapping("/insert")
    public ResponseEntity<?>add(@RequestBody Reservation reservation){
        try{
            Reservation reservation2 = reservationRepo.save(reservation);
            return new ResponseEntity<>("inserted",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("not inserted",HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getall")

    public ResponseEntity<?>getall(){
        try{
            List<Reservation>reservationList = reservationRepo.findAll();
            if(reservationList.isEmpty()){
                return new ResponseEntity<>("hamna data", HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity<>(reservationList,HttpStatus.OK);
            }
            
        }catch(Exception e){
            return new ResponseEntity<>("error",HttpStatus.CONFLICT);
        }
    }


    @GetMapping("/byId/{id}")
    
    public ResponseEntity<?>getbyId(@PathVariable int id){
        try{
            Optional<Reservation>optionalreservation = reservationRepo.findById(id);
            if(optionalreservation.isPresent()){
                return new ResponseEntity<>(optionalreservation,HttpStatus.OK);

            }else{
                return new ResponseEntity<>("hamna data",HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
        }
    }
    

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteID(@PathVariable int id){
        try{
            reservationRepo.deleteById(id);
            return new ResponseEntity<>("umefuta",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("haijafutika", HttpStatus.BAD_REQUEST);
        }
    }



   @PutMapping("/update/{id}")
   public ResponseEntity<?>change(@PathVariable int id, @RequestBody Reservation reservation){
    try{
        if(reservationRepo.findById(id).isPresent()){
            Reservation reservation2 = reservationRepo.save(reservation);

            reservation2.setCheckInDate(reservation.getCheckInDate());
            reservation2.setCheckOutDate(reservation.getCheckOutDate());

            Reservation change =reservationRepo.save(reservation2);
            return new ResponseEntity<>("imebadilika",HttpStatus.OK);

        }else{
            return new ResponseEntity<>("haijabadilika",HttpStatus.NOT_FOUND);
        }
        
    }catch (Exception e){
        return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
    }
   }
}
