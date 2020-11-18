package evilNerd.controller;

import evilNerd.controller.request.SearchCriteria;
import evilNerd.service.CarsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
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



}
