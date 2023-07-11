package kr.co.arterium.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserViewController {
    @RequestMapping("/users/sign-up")
    public String signUpForm(){
        return "users/sign-up";
    }
}
