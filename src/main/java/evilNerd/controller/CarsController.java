package evilNerd.controller;

import evilNerd.controller.request.CarsCreateRequest;
import evilNerd.controller.request.SearchCriteria;

import evilNerd.domain.Cars;
import evilNerd.service.CarsService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarsController {

    public final CarsService carsService;

    public static final String CARS_PAGE = "cars";
    public static final String CARS_LIST_ATTRIBUTE = "cars";

    //cars/
    @GetMapping
    public ModelAndView getHelloPage(){
        ModelAndView result = new ModelAndView();
        result.setViewName(CARS_PAGE);
        result.addObject(CARS_LIST_ATTRIBUTE, carsService.findAll());
        return result;

    }
    //cars/search

    // Query params handling
    // 1)RequestParam
    // 2)ModelAttribute
    // 3)ModelMap and get query param by key from map

 /*   @GetMapping(value = "search")
   // public ModelAndView search(@RequestParam("query") String queryParam, @RequestParam("limit") Long limit){
    public ModelAndView search(ModelMap modelMap){

        ModelAndView result = new ModelAndView();

        String resultQuery = StringUtils.isNotBlank((String)modelMap.get("query")) ?
                (String)modelMap.get("query") : "Sergey Auto";
        Long resultLimit = (Long)modelMap.get("limit") != null ? (Long)modelMap.get("limit") : 100;

        result.setViewName(CARS_PAGE);
        result.addObject(CARS_LIST_ATTRIBUTE, carsService.search(resultQuery).stream().limit(resultLimit).collect(Collectors.toList()));
        return result;
    }

  */


    @GetMapping(value = "search")
    // public ModelAndView search(@RequestParam("query") String queryParam, @RequestParam("limit") Long limit){
    public ModelAndView search(@ModelAttribute SearchCriteria criteria){

        ModelAndView result = new ModelAndView();
        result.setViewName(CARS_PAGE);
        result.addObject(CARS_LIST_ATTRIBUTE, carsService.search(criteria.getQuery()).stream().limit(criteria.getLimit()).collect(Collectors.toList()));
        return result;

    }

    //cars/1
    @GetMapping(value = "/{id}")
    // public ModelAndView search(@RequestParam("query") String queryParam, @RequestParam("limit") Long limit){
    public ModelAndView search(@PathVariable("id") Long carsId){

        ModelAndView result = new ModelAndView();
        result.setViewName(CARS_PAGE);
        result.addObject(CARS_LIST_ATTRIBUTE, Collections.singletonList(carsService.findById(carsId)));
        return result;

    }

    //cars/create
    @GetMapping("/create")
    public ModelAndView getCarsCreateRequest(){
        ModelAndView result = new ModelAndView();
        result.setViewName("createcars");
        result.addObject("carsCreateRequest", new CarsCreateRequest());
        return result;
    }

    @PostMapping
    public ModelAndView createCars(@ModelAttribute CarsCreateRequest carsCreateRequest){

        Cars cars = new Cars();
        cars.setModel(carsCreateRequest.getModel());
        cars.setCreationYear(carsCreateRequest.getCreationYear());
        cars.setUserId(carsCreateRequest.getUserId());
        cars.setPrice(carsCreateRequest.getPrice());
        cars.setColor(carsCreateRequest.getColor());

        carsService.save(cars);

        ModelAndView result = new ModelAndView();
        result.setViewName(CARS_PAGE);
        result.addObject(CARS_LIST_ATTRIBUTE, carsService.findAll());

        return result;

    }



}
