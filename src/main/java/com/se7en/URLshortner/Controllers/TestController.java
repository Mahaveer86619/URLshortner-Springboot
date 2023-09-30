package com.se7en.URLshortner.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/normal")
    public ResponseEntity<String > normalUser (){
        return ResponseEntity.ok("Yes I am normal User");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> adminUser (){
        return ResponseEntity.ok("Yes I am admin User");
    }

    @GetMapping("/public")
    public ResponseEntity<String> publicUser (){
        return ResponseEntity.ok("Yes I am public User");
    }

    @GetMapping("/currentUser")
    public ResponseEntity<String> getCurrnentUser(Principal principal){
        return ResponseEntity.ok(principal.getName());
    }
}
