package evilNerd.controller;


import evilNerd.controller.request.CarsCreateRequest;
import evilNerd.controller.request.SearchCriteria;
import evilNerd.domain.Cars;
import evilNerd.domain.User;
import evilNerd.service.CarsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/cars")
@RequiredArgsConstructor
public class CarsRestController {
    public final CarsService carsService;

    @GetMapping
    public ResponseEntity<List<Cars>> getFindAllUser(){
        return ResponseEntity.ok(carsService.findAll());
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cars findCarsById(@PathVariable Long id) {
        return carsService.findById(id);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<Cars> carsSearch(@ModelAttribute SearchCriteria search){
        return carsService.search(search.getQuery());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cars savingCars(@RequestBody CarsCreateRequest carsCreateRequest){

        Cars cars = new Cars();
        cars.setColor(carsCreateRequest.getColor());
        cars.setPrice(carsCreateRequest.getPrice());
        cars.setUserId(carsCreateRequest.getUserId());
        cars.setModel(carsCreateRequest.getModel());
        cars.setCreationYear(carsCreateRequest.getCreationYear());
        return carsService.save(cars);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cars updateCars(@PathVariable Long id,
                           @RequestBody CarsCreateRequest carsCreateRequest){

        Cars cars = carsService.findById(id);
        cars.setColor(carsCreateRequest.getColor());
        cars.setPrice(carsCreateRequest.getPrice());
        cars.setUserId(carsCreateRequest.getUserId());
        cars.setModel(carsCreateRequest.getModel());
        cars.setCreationYear(carsCreateRequest.getCreationYear());
        return carsService.save(cars);
    }

}
