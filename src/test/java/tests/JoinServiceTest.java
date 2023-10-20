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
                    fieldEachCheck(member, "아이디");

                    member.setUserId("  ");
                    fieldEachCheck(member, "아이디");
        },
                () -> {
                    //비밀번호 검증(UserPw)
                    Member member = getMember();
                    member.setUserPw(null);
                    fieldEachCheck(member, "비밀번호");

                    member.setUserPw("  ");
                    fieldEachCheck(member, "비밀번호");
                },
                () -> {
                    //비밀번호 확인(ConfirmUserPw)
                    Member member = getMember();
                    member.setConfirmUserPw(null);
                    fieldEachCheck(member, "비밀번호를 확인");

                    member.setConfirmUserPw("  ");
                    fieldEachCheck(member, "비밀번호를 확인");
                },
                () -> {
                    //회원명 검증(userNm)
                    Member member = getMember();
                    member.setUserNm(null);
                    fieldEachCheck(member, "회원명");

                    member.setUserNm("  ");
                    fieldEachCheck(member, "회원명");
                },
                () -> {
                    //이메일 검증(email)
                    Member member = getMember();
                    member.setEmail(null);
                    fieldEachCheck(member, "이메일");

                    member.setEmail("  ");
                    fieldEachCheck(member, "이메일");
                },
                () -> {
                    //약관동의 검증(agree)
                    Member member = getMember();
                    member.setAgree(false);
                    fieldEachCheck(member, "회원가입 약관");
                }
        );
    }

    private void fieldEachCheck(Member member, String word) { // 필수 항목 검증할때 정확한 검증을 위한
        BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
           joinService.join(member);
        });
        assertTrue(thrown.getMessage().contains(word));
    }

    @Test
    @DisplayName("아이디(6자리 이상), 비밀번호 최소 자리수 체크(8자리 이상), 실패시 BadRequestException 발생")
    void fieldLengthCheck() {
        assertAll(
                () -> {
                    //아이디 6자리 이상 검증
                    Member member = getMember();
                    member.setUserId("user");
                    fieldEachCheck(member, "아이디는 6자리");
        },
                () -> {
                    //비밀번호 8자리 이상 검증
                    Member member = getMember();
                    member.setUserPw("1234");
                    fieldEachCheck(member, "비밀번호는 8자리");
                }
        );
    }

    @Test
    @DisplayName("비밀번호, 비밀번호 확인 입력 데이터 일치여부 체크, 검증 실패시 BadRequestException 발생")
    void passwordConfirmCheck() {
        BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
           Member member = getMember();
           member.setConfirmUserPw(member.getUserPw() + "**");
           joinService.join(member);
        });

        assertTrue(thrown.getMessage().contains("비밀번호가 일치"));
    }

    @Test
    @DisplayName("중복 가입 체크, 중복 가입인 경우 DuplicateMemberException 발생")
    void duplicateJoinCheck() {
        assertThrows(DuplicateMemberException.class, () -> {
           Member member = getMember();
           joinService.join(member);

           joinService.join(member); // 동일한 정보를 통해 두번 가입
        });
    }
}
