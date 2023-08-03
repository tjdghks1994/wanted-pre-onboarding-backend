package com.wanted.controller.api;

import com.wanted.domain.JoinForm;
import com.wanted.domain.LoginInfo;
import com.wanted.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Slf4j
@RestController
@RequestMapping("/members")
public class MemberApiController {
    private final MemberService memberService;
    private final MessageSource messageSource;

    public MemberApiController(MemberService memberService, MessageSource messageSource) {
        this.memberService = memberService;
        this.messageSource = messageSource;
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@Validated @RequestBody JoinForm joinForm, BindingResult bindingResult) {
        log.debug("joinForm = {}", joinForm);
        // 이메일 @ 포함 여부 체크
        if (!(joinForm.getJoinMail().contains("@"))) {
            bindingResult.rejectValue("joinMail", "PatternFail", "@를 포함해야 합니다");
        }
        // 검증 오류 발생 - 회원가입 페이지로
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            String errorCode = Arrays.stream(fieldErrors.get(0).getCodes()).findFirst().get();
            // bindingResult 에서 반환되는 필드 에러가 발생한 코드 값을 이용해서 messageSource 를 활용해 errors.properties 에 등록된 값 가져오기
            String validMessage = messageSource.getMessage(errorCode, null, Locale.KOREA);

            return new ResponseEntity<>(validMessage, HttpStatus.BAD_REQUEST);
        }
        // 회원 가입
        memberService.joinMember(joinForm);

        // 회원 가입성공
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginProc(@Validated @RequestBody LoginInfo member, BindingResult bindingResult) {
        // 이메일 @ 포함 여부 체크
        if (!(member.getMemberId().contains("@"))) {
            bindingResult.rejectValue("memberId", "PatternFail", "@를 포함해야 합니다");
        }
        // 이메일, 패스워드 검증
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            String errorCode = Arrays.stream(fieldErrors.get(0).getCodes()).findFirst().get();
            // bindingResult 에서 반환되는 필드 에러가 발생한 코드 값을 이용해서 messageSource 를 활용해 errors.properties 에 등록된 값 가져오기
            String validMessage = messageSource.getMessage(errorCode, null, Locale.KOREA);

            return new ResponseEntity<>(validMessage, HttpStatus.BAD_REQUEST);
        }

        // 로그인 성공 시 jwt 반환
        String loginJwt = memberService.login(member.getMemberId(), member.getMemberPw());

        return new ResponseEntity<>(loginJwt, HttpStatus.OK);
    }
}
