package com.kackan.system_to_manage_cars_with_database.controller;

import com.kackan.system_to_manage_cars_with_database.exception.IdDuplicationException;
import com.kackan.system_to_manage_cars_with_database.model.Car;
import com.kackan.system_to_manage_cars_with_database.repository.CarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin
public class CarController {

    private final CarDao carService;

    @Autowired
    public CarController(CarDao carService) {
        this.carService = carService;
    }


    @GetMapping
    public ResponseEntity<List<Car>> getAllLimitedToYears(@RequestParam(required = false, defaultValue = "0") int yearDownLimit, @RequestParam(required = false, defaultValue = "3000") int yearUpLimit)
    {
        return new ResponseEntity<>(carService.findAllByYear(yearDownLimit, yearUpLimit), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable long id)
    {
        return Optional.of(carService.getOne(id)).map(c->new ResponseEntity<>(c,HttpStatus.OK)).orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> addCar(@RequestBody Car car)
    {
        try {
            return Optional.of(carService.save(car)).map(c->new ResponseEntity<>(car,HttpStatus.OK)).orElseGet(()->new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        } catch (IdDuplicationException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Car> removeCar(@PathVariable long id)
    {
        return Optional.of(carService.delete(id)).map(c->new ResponseEntity<>(c,HttpStatus.OK)).orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@RequestBody Car car, @PathVariable long id){
        return Optional.of(carService.update(car, id)).map(c->new ResponseEntity<>(c,HttpStatus.OK)).orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
