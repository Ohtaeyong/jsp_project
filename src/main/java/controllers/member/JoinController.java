package controllers.member;

import static commons.ScriptUtil.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.member.JoinService;
import models.member.ServiceManager;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/member/join")
public class JoinController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("addCss", new String[] { "join"} ); // jsp에서 css경로 설정하지않고 컨트롤러에서 직접
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/templates/member/join.jsp");
        rd.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            JoinService service = ServiceManager.getInstance().joinService();
            service.join(req);

            //resp.sendRedirect(req.getContextPath() + "/member/login");
            String url = req.getContextPath() + "/member/login";
            go(resp, url, "parent"); // 회원가입 성공시에는 로그인페이지로 이동

        } catch (RuntimeException e) {
            alertError(resp, e);
        }
    }
}
