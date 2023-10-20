package models.member;

import commons.Validator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginService {

    private Validator<HttpServletRequest> validator;

    private MemberDao memberDao;

    public LoginService(Validator<HttpServletRequest> validator, MemberDao memberDao) {
        this.validator = validator;
        this.memberDao = memberDao;
    }

    public void login(HttpServletRequest request) {
        validator.check(request);

        //로그인 처리
        String userId = request.getParameter("userId"); // 로그인이 성공하였으니 아이디가 있음
        Member member = memberDao.get(userId);
        HttpSession session = request.getSession();
        session.setAttribute("member", member);
    }
}
