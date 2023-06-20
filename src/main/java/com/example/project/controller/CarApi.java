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

import com.example.project.model.Car;
import com.example.project.repository.CarRepo;

@RestController
@RequestMapping("/car")
@CrossOrigin("http://localhost:3000")
public class CarApi {
    @Autowired
    private CarRepo carRepo;

    @GetMapping("/allcars")

    public ResponseEntity<?>getcars(){
        try{
            List<Car>carList = carRepo.findAll();
            if(carList.isEmpty()){
                return new ResponseEntity<>("hamna data", HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity<>(carList,HttpStatus.OK);
            }
        }catch(Exception exception){
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getbyID/{id}")

    public ResponseEntity<?>carbyID(@PathVariable int id){
        try{
            Optional<Car>optionalcar = carRepo.findById(id);
            if(optionalcar.isPresent()){
                return new ResponseEntity<>(optionalcar,HttpStatus.OK);
            }else{
                return new ResponseEntity<>("hamna data",HttpStatus.NOT_FOUND);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("system haipatikani",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?>deletecar(@PathVariable int id){
        try{
            carRepo.deleteById(id);
            return new ResponseEntity<>("successfully deleted",HttpStatus.OK);
        }catch(Exception exception){
            return new ResponseEntity<>("hmn data ya kufuta",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/insert")
    public ResponseEntity<?>addcar(@RequestBody Car car){
        try{
            Car car2 = carRepo.save(car);
            return new ResponseEntity<>("inserted",HttpStatus.OK);
        }catch(Exception exception){
            return new ResponseEntity<>("haijaingya",HttpStatus.BAD_REQUEST);
        }
    }

  
    @PutMapping("/update/{id}")

    public ResponseEntity<?>updatecar(@PathVariable int id, @RequestBody Car car){
        try{
            if (carRepo.findById(id).isPresent()){

                
                
                Car car2 = carRepo.save(car);
                
                // car2.setPlateNo(car.getPlateNo());
                car2.setCarType(car.getCarType());
                car2.setCarNumber(car.getCarNumber());
                
                Car updateCar = carRepo.save(car2);
                return new ResponseEntity<>("updated",HttpStatus.OK);
            }else{
                return new ResponseEntity<>("bado update",HttpStatus.CONFLICT);
            }
        }catch(Exception exception){
            return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
        }
    }
  
}
