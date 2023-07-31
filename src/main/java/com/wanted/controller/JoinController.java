package com.wanted.controller;

import com.wanted.domain.JoinForm;
import com.wanted.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/members")
public class JoinController {

    private final MemberService memberService;
    @Autowired
    public JoinController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/join")
    public String joinForm(Model model) {
        JoinForm joinForm = new JoinForm();
        model.addAttribute("joinForm", joinForm);

        return "join/joinForm";
    }

    @PostMapping("/join")
    public String join(@Validated @ModelAttribute JoinForm joinForm, BindingResult bindingResult) {
        log.debug("joinForm = {}", joinForm);
        // 이메일 @ 포함 여부 체크
        if (!(joinForm.getJoinMail().contains("@"))) {
            bindingResult.rejectValue("joinMail", "PatternFail", "@를 포함해야 합니다");
        }
        // 검증 오류 발생 - 회원가입 페이지로
        if (bindingResult.hasErrors()) {
            log.debug("bindingResult = {}", bindingResult);
            return "join/joinForm";
        }
        // 회원 가입
        memberService.joinMember(joinForm);

        // 회원 가입성공 - 로그인 페이지로
        return "redirect:/members/login";
    }
}
