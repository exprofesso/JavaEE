package evilNerd.controller;


import evilNerd.controller.request.SearchCriteria;
import evilNerd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;

    public static final String USER_PAGE = "users";
    public static final String USER_LIST_ATTRIBUTE = "users";

    //users/
    @GetMapping
    public ModelAndView getHelloPage() {
        ModelAndView result = new ModelAndView();
        result.setViewName(USER_PAGE);
        result.addObject(USER_LIST_ATTRIBUTE, userService.findAll());
        return result;
    }

    //users/search
    @GetMapping(value = "search")
   // public ModelAndView usersearch(@RequestParam("query") String queryParam, @RequestParam("limit") Long limit) {
    public ModelAndView usersearch(@ModelAttribute SearchCriteria criteria) {

        ModelAndView result = new ModelAndView();
        result.setViewName(USER_PAGE);
        result.addObject(USER_LIST_ATTRIBUTE, userService.search(criteria.getQuery()).stream().limit(criteria.getLimit()).collect(Collectors.toList()));
        return result;
    }

    //users/1
    @GetMapping(value = "/{id}")
    // public ModelAndView usersearch(@RequestParam("query") String queryParam, @RequestParam("limit") Long limit) {
    public ModelAndView usersearch(@PathVariable("id") Long userId) {

        ModelAndView result = new ModelAndView();
        result.setViewName(USER_PAGE);
        result.addObject(USER_LIST_ATTRIBUTE, Collections.singletonList(userService.findById(userId)));
        return result;
    }




}

