package models.member;


import java.util.HashMap;
import java.util.Map;

public class MemberDao {
    private static Map<String, Member> members = new HashMap<>();

    //중복여부 체크를 위한 회원가입
    public void register(Member member) {
        members.put(member.getUserId(), member);
    }

    public Member get(String userId) {
        return members.get(userId); // 없을 시 null값
    }

    //회원이 존재하는가의 여부
    public boolean exists(String userId) {
        return members.containsKey(userId); // 키가 존재시 회원이 있다.
    }
}
