package models.member;

import commons.BadRequestException;
import commons.LengthValidator;
import commons.RequiredValidator;
import commons.Validator;

public class JoinValidator implements Validator<Member>, RequiredValidator, LengthValidator {

    @Override
    public void check(Member member) {
        String userId = member.getUserId();
        String userPw = member.getUserPw();
        String confirmUserPw = member.getConfirmUserPw();

        //필수 항목 검증 s
        requiredCheck(userId, new BadRequestException("아이디를 입력하세요."));
        requiredCheck(userPw, new BadRequestException("비밀번호를 입력하세요."));
        requiredCheck(confirmUserPw, new BadRequestException("비밀번호를 확인하세요."));
        requiredCheck(member.getUserNm(), new BadRequestException("회원명을 입력하세요."));
        requiredCheck(member.getEmail(), new BadRequestException("이메일을 입력해주세요."));
        requiredTrue(member.getAgree(), new BadRequestException("회원가입 약관에 동의하세요."));
        //필수 항목 검증 e

        //아이디 비밀번호 길이(6자리, 8자리) s
        lengthCheck(userId, 6, new BadRequestException("아이디는 6자리 이상 입력하세요"));
        lengthCheck(userPw, 8, new BadRequestException("비밀번호는 8자리 이상 입력하세요."));
        //아이디 비밀번호 길이(6자리, 8자리) e

    }
}
