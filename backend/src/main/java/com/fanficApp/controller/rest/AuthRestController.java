package com.fanficApp.controller.rest;

import com.fanficApp.dto.response.AuthResponse;
import com.fanficApp.dto.response.Error;
import com.fanficApp.dto.response.Message;
import com.fanficApp.entity.User;
import com.fanficApp.repository.UserRepo;
import com.fanficApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<?> signUp(@RequestBody User user) {
        AuthResponse response;
        try {
            response = userService.registerUser(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Error(e.getMessage()));
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signin")
    @ResponseBody
    public ResponseEntity<?> signIn(@RequestBody User user) {
        AuthResponse response;
        try {
            response = userService.loginUser(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Error(e.getMessage()));
        }
        return ResponseEntity.ok(response);
    }

}
