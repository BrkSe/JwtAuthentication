package com.bhanuka.jwtauthdemo.demo.controller;

import com.bhanuka.jwtauthdemo.demo.model.AuthRequest;
import com.bhanuka.jwtauthdemo.demo.model.Token;
import com.bhanuka.jwtauthdemo.demo.service.AuthService;
import com.bhanuka.jwtauthdemo.demo.service.JwtTokenService;
import com.bhanuka.jwtauthdemo.demo.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/security")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
        authService.authenticate(authRequest.getUsername(), authRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String token = jwtTokenService.generateToken(userDetails);
        return ResponseEntity.ok(new Token(token));
    }

}
