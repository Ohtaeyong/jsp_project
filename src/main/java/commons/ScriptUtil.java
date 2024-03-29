package commons;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class ScriptUtil {
    public static void alertError(HttpServletResponse resp, Exception e) throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.printf("<script>alert('%s');</script>", e.getMessage());

        e.printStackTrace();
    }

    public static void go(HttpServletResponse resp, String url, String target) throws IOException {
        target = Objects.requireNonNullElse(target, "self");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.printf("<script>%s.location.replace('%s');</script>", target, url); //입력한 주소대로 이동
    }

    public static void go(HttpServletResponse resp, String url) throws IOException {
        go(resp, url, null); //현재창으로 이동 target => self
    }
}