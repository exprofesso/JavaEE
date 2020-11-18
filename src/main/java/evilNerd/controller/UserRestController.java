package evilNerd.controller;


import evilNerd.domain.User;
import evilNerd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
