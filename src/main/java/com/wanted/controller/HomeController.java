package com.wanted.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Authentication authentication, Model model) {
        log.debug("[home page]");
        // 인증이 완료된 경우
        if (authentication != null) {
            log.debug("{} 님이 메인페이지로 접근하였습니다.", authentication.getName());
            model.addAttribute("username", authentication.getName());
            return "index";
        }
        // 인증이 완료되지 않은 경우 - 로그인 페이지
        return "redirect:/members/login";
    }
}
