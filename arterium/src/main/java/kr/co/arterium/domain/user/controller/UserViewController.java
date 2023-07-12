package kr.co.arterium.domain.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserViewController {
    /**
     * sign-in 로그인
     * */
    @RequestMapping("/users/sign-in")
    public String signInForm(){
        return "users/sign-in";
    }

    /**
     * sign-up 회원가입
     * @return
     */
    @RequestMapping("/users/sign-up")
    public String signUpForm(){
        return "users/sign-up";
    }
}
