package com.fanficApp.controller.rest;

import com.fanficApp.entity.User;
import com.fanficApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/search/by/name/{name}")
    public ResponseEntity<User> findById(@PathVariable("name") String name) {
        return ResponseEntity.ok((User) userService.loadUserByUsername(name));
    }

    @GetMapping(value = "/all")
    public List<User> findAll() {
        return userService.findAll();
    }

}
