package models.member;

import commons.BadRequestException;
import commons.RequiredValidator;
import commons.Validator;

public class JoinValidator implements Validator<Member>, RequiredValidator {

    @Override
    public void check(Member member) {
        //필수 항목 검증 s
        requiredCheck(member.getUserId(), new BadRequestException("아이디를 입력하세요."));
        requiredCheck(member.getUserPw(), new BadRequestException("비밀번호를 입력하세요."));
        requiredCheck(member.getConfirmUserPw(), new BadRequestException("비밀번호를 확인하세요."));
        requiredCheck(member.getUserNm(), new BadRequestException("회원명을 입력하세요."));
        requiredCheck(member.getEmail(), new BadRequestException("이메일을 입력해주세요."));
        requiredTrue(member.getAgree(), new BadRequestException("회원가입 약관에 동의하세요."));
        //필수 항목 검증 e
    }
}
