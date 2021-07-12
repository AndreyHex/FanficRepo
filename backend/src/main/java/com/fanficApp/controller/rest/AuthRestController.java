package com.fanficApp.controller.rest;

import com.fanficApp.dto.response.AuthResponse;
import com.fanficApp.dto.response.Error;
import com.fanficApp.dto.response.Message;
import com.fanficApp.entity.User;
import com.fanficApp.repository.UserRepo;
import com.fanficApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/current")
    public ResponseEntity<User> getCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(user);
    }

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<?> signUp(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.registerUser(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Error(e.getMessage()));
        }
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
