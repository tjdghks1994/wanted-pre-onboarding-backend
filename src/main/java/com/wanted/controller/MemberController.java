package com.wanted.controller;

import com.wanted.domain.JoinForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/members")
public class MemberController {

    @GetMapping("/join")
    public String joinForm(Model model) {
        JoinForm joinForm = new JoinForm();
        model.addAttribute("joinForm", joinForm);

        return "join/joinForm";
    }

    @GetMapping("/login")
    public String loginForm() {
        log.debug("[login page start]");

        return "login/loginForm";
    }
}
