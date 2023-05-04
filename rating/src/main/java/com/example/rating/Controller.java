package com.example.rating;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class Controller {
    private RestaurantService restorentService;

    public Controller(RestaurantService restorentService) {
        this.restorentService = restorentService;
    }

    @PostMapping
    public ResponseEntity<Restaurant> saveRestorent(@RequestBody Restaurant restorent) {
        restorent.setAvg( ((double)(restorent.getFood() + restorent.getAmbience() + restorent.getCleanliness() + restorent.getService()) / 4));
        return new ResponseEntity<Restaurant>(restorentService.saveRestorent(restorent), HttpStatus.CREATED);
    }
    @GetMapping()
    public List<Restaurant> getAllRestaurant(){
        return restorentService.getAllRestaurant();
    }

    @PutMapping("{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable(name = "id") int id,
                                                     @RequestBody Restaurant restaurant){

        return new ResponseEntity<Restaurant>(restorentService.updateRestaurant(restaurant,id),HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(name = "id") int id){
        restorentService.deleteRestaurant(id);

        return new ResponseEntity<String>("Restaurant Deleted Sucessfully!",HttpStatus.OK);
    }

    @GetMapping("/avg/{id}")
    public ResponseEntity<Restaurant> getAverage(@PathVariable(name = "id") int id,
                                                 @RequestBody Restaurant restaurant){

        return new ResponseEntity<Restaurant>(restorentService.getAvg(restaurant,id),HttpStatus.OK);
    }
    @GetMapping("/top10/")
    public HashMap<String, Double> getTop10(){

        return restorentService.getTop10();
    }
}