package com.example.demo.controller;

import com.example.demo.model.ApplicationUser;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.interfaces.RecaptchaService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import java.util.HashMap;
import java.util.Map;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Controller
public class AuthController {
    @Autowired
    private RecaptchaService recaptchaService;
    @Autowired
    private UserRepository userRepository;
    @RequestMapping("/auth")


    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
    @PostMapping("/login")
    public ResponseEntity<?> signup(@Valid ApplicationUser user,
                                    @RequestParam(name="g-recaptcha-response") String recaptchaResponse,
                                    HttpServletRequest request
    ){

        String ip = request.getRemoteAddr();
        String captchaVerifyMessage =
                recaptchaService.verifyRecaptcha(ip, recaptchaResponse);

        if ( StringUtils.isNotEmpty(captchaVerifyMessage)) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", captchaVerifyMessage);
            return ResponseEntity.badRequest()
                    .body(response);
        }
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/success")
    public String getSuccessPage() {
        return "success";
    }


}
