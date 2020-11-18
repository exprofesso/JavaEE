package evilNerd.controller;


import evilNerd.controller.request.SearchCriteria;
import evilNerd.controller.request.UserCreateRequest;
import evilNerd.domain.User;
import evilNerd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/rest/users")
@RequiredArgsConstructor
public class UserRestController {

    public final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAllUser(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK); // полный вариант
        //return ResponseEntity.ok(userService.findAll()); // краткий вариант
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User findUserById(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<User> userSearch(@ModelAttribute SearchCriteria search){
        return userService.search(search.getQuery());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody UserCreateRequest userCreateRequest){
        User user = new User();
        user.setName(userCreateRequest.getName());
        user.setSurname(userCreateRequest.getSurname());
        user.setBirthDate(userCreateRequest.getBirthDate());
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        user.setChanged(new Timestamp(System.currentTimeMillis()));
        user.setGender(userCreateRequest.getGender());

        return userService.save(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@PathVariable Long id, @RequestBody UserCreateRequest userCreateRequest){

        User user = userService.findById(id);
        user.setName(userCreateRequest.getName());
        user.setSurname(userCreateRequest.getSurname());
        user.setBirthDate(userCreateRequest.getBirthDate());
        user.setChanged(new Timestamp(System.currentTimeMillis()));
        user.setGender(userCreateRequest.getGender());

        return userService.save(user);
    }



}
