<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %> <%-- 로그인버튼을 눌렀을 시 페이지 이동x(iframe) --%>
<c:url var="action" value="/member/login" />
<layout:bodyOnly title="회원가입"> <%-- feader와 footer가 제거된 bodyOnly따로 제작해서 불러오기 --%>
    <form method="post" action"${action}" target="ifrmProcess">

    <div class="wrapper"> <%-- name값 설정할 것 --%>
        <div class="title"><h1 style="font-size: 21px;">회원가입</h1></div>
            <div class="Id">
                <input id="userId" name="userId" type="text" placeholder="아이디를 입력해 주세요.">
            </div>
            <div class="password">
                <input id="userPw" type="password" name="userPw" placeholder="비밀번호를 입력해 주세요.">
            </div>
            <div class="passwordCheck">
                <input id="confirmUserPw" type="password" name="confirmUserPw" placeholder="비밀번호를 다시 입력해 주세요.">
            </div>
            <div class="name">
                <input id="userNm"  type="text" name="userNm" placeholder="이름을 입력해 주세요.">
            </div>
            <div class="email">
                <input id="email" type="text" name="email" placeholder="이메일을 입력해 주세요.">
            </div>
            <div class="line">
                <hr>
            </div>
            <div>
                <input type="checkbox" name="agree" value="true" id="agree">
                <label for="agree">회원가입 약관에 동의합니다.</label>
            </div>
            <div class="signUp">
                <button type="submit">가입하기</button>
            </div>
        </div>
    </form>
</layout:bodyOnly>