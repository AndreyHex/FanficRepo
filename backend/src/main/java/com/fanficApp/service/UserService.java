package com.fanficApp.service;

import com.fanficApp.dto.response.AuthResponse;
import com.fanficApp.entity.Role;
import com.fanficApp.entity.User;
import com.fanficApp.jwt.JwtUtils;
import com.fanficApp.repository.RoleRepo;
import com.fanficApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Username not found.");
        }
        return user;
    }

    public User findUserById(Long id) {
        Optional<User> user = userRepo.findById(id);
        return user.orElse(new User());
    }

    public void saveUser(User user) throws EntityExistsException {
        if(userRepo.existsByUsername(user.getUsername())) {
            throw new EntityExistsException("User exists.");
        }
        user.setRoles(Collections.singleton(new Role(2L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setStatus(true);
        userRepo.saveAndFlush(user);
        roleRepo.flush();
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public AuthResponse loginUser(User user) throws Exception {
        if(user.getUsername() == null || user.getPassword() == null) throw new Exception("Needed username and password.");

        User u = userRepo.findByUsername(user.getUsername());
        if(u == null) throw new Exception("User not found");

        String token = authenticateUser(user);
        return new AuthResponse("Success", u, token);
    }

    public AuthResponse registerUser(User user) throws Exception {
        if(user.getUsername() == null || user.getPassword() == null) throw new Exception("Needed username and password.");

        User u = new User();
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());

        saveUser(user);
        String token = authenticateUser(u);
        user.setPassword("");
        return new AuthResponse("Register success", user, token);
    }

    private String authenticateUser(User user) throws Exception {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(!auth.isAuthenticated()) throw new Exception("Authentication error.");
        SecurityContextHolder.getContext().setAuthentication(auth);
        return jwtUtils.generateJwtToken(auth);
    }
}
