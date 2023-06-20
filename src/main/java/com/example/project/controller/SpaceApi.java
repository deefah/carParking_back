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

import com.example.project.model.Space;
import com.example.project.repository.SpaceRepo;









@RestController
@RequestMapping("/space")
public class SpaceApi {

    @Autowired
    public SpaceRepo spaceRepo;

    @PostMapping("/add")
    public ResponseEntity<?>add(@RequestBody Space space){
        try{
            Space space2 = spaceRepo.save(space);
            return new ResponseEntity<>("imnnnn",HttpStatus.OK);
        }catch(Exception exception){
            return new ResponseEntity<>("haijaingia",HttpStatus.BAD_GATEWAY);
        }
    }


    @GetMapping("/allspaces")

        public ResponseEntity<?>allspace(){
            try{
                List<Space>spaceList = spaceRepo.findAll();
                if (spaceList.isEmpty()){
                    return new ResponseEntity<>("hmna data",HttpStatus.BAD_REQUEST);
                }else{
                    return new ResponseEntity<>(spaceList,HttpStatus.OK);
                }
            }catch(Exception e){
                return new ResponseEntity<>("Error",HttpStatus.CONFLICT);
            }
        }



        @GetMapping("/byId/{id}")
        public ResponseEntity<?>getspace(@PathVariable int id){
            try{
                Optional<Space>optionalspace = spaceRepo.findById(id);
                if (optionalspace.isPresent()){
                    return new ResponseEntity<>(optionalspace,HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("hamna data",HttpStatus.BAD_REQUEST);
                }
            }catch(Exception e){
                return new ResponseEntity<>("error",HttpStatus.CONFLICT);
            }
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<?>delete(@PathVariable int id){
            try{
                spaceRepo.deleteById(id);
                return new ResponseEntity<>("deleted",HttpStatus.OK);
            }catch(Exception e){
                return new ResponseEntity<>("no data",HttpStatus.BAD_REQUEST);
            }
        }


        @PutMapping("/update/{id}")
        public ResponseEntity<?>change(@PathVariable int id, @RequestBody Space space){
            try{
                if (spaceRepo.findById(id).isPresent()){
                    Space space2 = spaceRepo.save(space);
                    space2.setSpaceNo(space.getSpaceNo());
                    space2.setSpaceDescr(space.getSpaceDescr());
                    Space change = spaceRepo.save(space2);
                    return new ResponseEntity<>("updated",HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("not updated",HttpStatus.CONFLICT);
                }
            }catch(Exception e){
                return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
            }
        }

      
    }
    


