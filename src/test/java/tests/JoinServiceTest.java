package tests;

import commons.BadRequestException;
import models.member.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("회원가입 기능 단위 테스트")
public class JoinServiceTest {

    private JoinService joinService;

    @BeforeEach
    void init() {
        joinService = ServiceManager.getInstance().joinService();
    }

    private Member getMember() {
        return Member.builder()
                .userId("user" + System.currentTimeMillis())
                .userPw("12345678")
                .confirmUserPw("12345678")
                .userNm("사용자")
                .email("user@test.org")
                .agree(true)
                .build();
    }

    @Test
    @DisplayName("회원가입 성공시 예외발생하지 않음")
    void joinSuccess() {
        assertDoesNotThrow(() -> {
            joinService.join(getMember());
        });
    }

    @Test
    @DisplayName("필수항목 검증(아이디, 비밀번호, 비밀번호 확인, 회원명, 이메일, 회원가입 동의), 검증 실패시 BadRequestException 발생")
    void requiredFieldCheck() {
        //아이디가 null또는 빈값일때
        assertAll(
                () -> {
                    //아이디 검증(UserId)
                    Member member = getMember();
                    member.setUserId(null);
                    requiredFieldEach(member, "아이디");

                    member.setUserId("  ");
                    requiredFieldEach(member, "아이디");
        },
                () -> {
                    //비밀번호 검증(UserPw)
                    Member member = getMember();
                    member.setUserPw(null);
                    requiredFieldEach(member, "비밀번호");

                    member.setUserPw("  ");
                    requiredFieldEach(member, "비밀번호");
                },
                () -> {
                    //비밀번호 확인(ConfirmUserPw)
                    Member member = getMember();
                    member.setConfirmUserPw(null);
                    requiredFieldEach(member, "비밀번호를 확인");

                    member.setConfirmUserPw("  ");
                    requiredFieldEach(member, "비밀번호를 확인");
                },
                () -> {
                    //회원명 검증(userNm)
                    Member member = getMember();
                    member.setUserNm(null);
                    requiredFieldEach(member, "회원명");

                    member.setUserNm("  ");
                    requiredFieldEach(member, "회원명");
                },
                () -> {
                    //이메일 검증(email)
                    Member member = getMember();
                    member.setEmail(null);
                    requiredFieldEach(member, "이메일");

                    member.setEmail("  ");
                    requiredFieldEach(member, "이메일");
                },
                () -> {
                    //약관동의 검증(agree)
                    Member member = getMember();
                    member.setAgree(false);
                    requiredFieldEach(member, "회원가입 약관");
                }
        );
    }

    private void requiredFieldEach(Member member, String word) { // 필수 항목 검증할때 정확한 검증을 위한
        BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
           joinService.join(member);
        });
        assertTrue(thrown.getMessage().contains(word));
    }
}
