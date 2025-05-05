package com.raymond.controller;

import com.raymond.service.SystemUserService;
import com.raymond.service.security.JwtUserDetailsService;
import com.raymond.utils.AjaxResult;
import com.raymond.utils.JWTTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class AuthController {

    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @PostMapping("/pwd")
    public AjaxResult createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = jwtUserDetailsService.
                loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails.getUsername());
        return AjaxResult.success("登录成功",token);
//
    }
}

class JwtRequest {
    private String username;
    private String password;

    // getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
