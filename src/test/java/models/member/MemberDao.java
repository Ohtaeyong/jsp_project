package models.member;


import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;

public class MemberDao {
    private static Map<String, Member> members = new HashMap<>();

    //중복여부 체크를 위한 회원가입
    public void register(Member member) {
        String userPw = BCrypt.hashpw(member.getUserPw(), BCrypt.gensalt(12)); // 유동해시를 이용한 암호화 -> 12번반복
        member.setUserPw(userPw);

        members.put(member.getUserId(), member);
    }

    public Member get(String userId) {
        return members.get(userId); // 없을 시 null값
    }

    //회원이 존재하는가의 여부
    public boolean exists(String userId) {
        return members.containsKey(userId); // 키가 존재시 회원이 있다.
    }

    public static void clearData() {
        members.clear(); // 임시로 추가
    }
}
