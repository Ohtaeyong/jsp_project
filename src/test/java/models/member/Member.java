package models.member;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Member {
    private String userId;
    private String userPw;
    private String confirmUserPw;
    private String userNm;
    private String email;
    private Boolean agree; // 약관동의
}
