package evilNerd.controller;


import evilNerd.config.AmazonConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/info/amazon")
@RequiredArgsConstructor
public class AmazonRestController {

    @Autowired
    public final AmazonConfig amazonConfig;

    @GetMapping
   public ResponseEntity<AmazonConfig> amazonInfo(){
        return new ResponseEntity<>(amazonConfig, HttpStatus.OK);
    }


}
