package com.wanted.controller;

import com.wanted.domain.JoinForm;
import jakarta.servlet.http.Cookie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/members")
public class MemberController {

    @GetMapping("/join")
    public String joinForm() {

        return "join/joinForm";
    }

    @GetMapping("/login")
    public String loginForm(@CookieValue(value = "access_token", required = false) Cookie cookie) {
        log.debug("[login page start]");;
        if (cookie != null) {
            return "redirect:/";
        }

        return "login/loginForm";
    }
}
