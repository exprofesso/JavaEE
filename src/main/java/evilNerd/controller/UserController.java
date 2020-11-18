package evilNerd.controller;


import evilNerd.controller.request.SearchCriteria;
import evilNerd.controller.request.UserCreateRequest;
import evilNerd.domain.User;
import evilNerd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;

    public static final String USER_PAGE = "users";
    public static final String USER_LIST_ATTRIBUTE = "users";


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }



    //users/
    @GetMapping
    public ModelAndView getHelloPage() {
        ModelAndView result = new ModelAndView();
        result.setViewName(USER_PAGE);
        result.addObject(USER_LIST_ATTRIBUTE, userService.findAll());
        return result;
    }
    //users/search

    // Query params handling
    // 1)RequestParam
    // 2)ModelAttribute
    // 3)ModelMap and get query param by key from map

/*    @GetMapping(value = "search")
   // public ModelAndView usersearch(@RequestParam("query") String queryParam, @RequestParam("limit") Long limit) {
    public ModelAndView usersearch(ModelMap modelMap) {

        ModelAndView result = new ModelAndView();

        String resultQuery = StringUtils.isNotBlank((String)modelMap.get("query")) ?
                (String)modelMap.get("query") : "Sergey";

        Long resultLimit = (Long)modelMap.get("limit") != null ? (Long)modelMap.get("limit") : 100;


        result.setViewName(USER_PAGE);
        result.addObject(USER_LIST_ATTRIBUTE, userService.search(resultQuery).stream().limit(resultLimit).collect(Collectors.toList()));
        return result;
    }
 */

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
    @GetMapping("/create")
    public ModelAndView getUserCreateRequest(){
        ModelAndView result = new ModelAndView();
        result.setViewName("createuser");
        result.addObject("userCreateRequest", new UserCreateRequest());
        return result;
    }

    @PostMapping
    public ModelAndView createUser(@ModelAttribute UserCreateRequest userCreateRequest){

        //converter

        User user = new User();
        user.setName(userCreateRequest.getName());
        user.setSurname(userCreateRequest.getSurname());
        user.setBirthDate(userCreateRequest.getBirthDate());
        user.setGender(userCreateRequest.getGender());
        user.setWeight(userCreateRequest.getWeight());

        userService.save(user);

        ModelAndView result = new ModelAndView();
        result.setViewName(USER_PAGE);
        result.addObject(USER_LIST_ATTRIBUTE, userService.findAll());

        return result;



    }




}

