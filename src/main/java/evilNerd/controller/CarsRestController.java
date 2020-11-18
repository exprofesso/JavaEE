package evilNerd.controller;


import evilNerd.domain.Cars;
import evilNerd.service.CarsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
