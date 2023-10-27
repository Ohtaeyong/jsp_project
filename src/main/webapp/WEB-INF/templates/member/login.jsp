<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<c:url var="action" value="/member/login" />
<c:url var="mainUrl" value="/" />
<c:url var="joinUrl" value="/member/join" />
<layout:bodyOnly title="로그인">
    <div class="container">
            <h5><span>로그인</span> 페이지입니다.</h5>
        <hr />

    <form method="post" action="${action}" target="ifrmProcess">
        <input type="text" placeholder="아이디" name="userId" value="${cookie.saveId.value}" required style="height:30px; width: 380px" /><br>
        <input type="password" placeholder="비밀번호" name="userPw" required style="height:30px; width: 380px" /><br />
        <div>
            <input type="checkbox" name="saveId" value="true" id="saveId"${cookie.saveId == null ? '' : 'checked'}>
            <label for="saveId">아이디 저장</label>
        </div>
        <button type="submit" class="login">로그인</button><%--버튼태그를 닫을것--%>
        <a href="${mainUrl}"><input type="button" value="HOME" class="login1" /></a>
    </form>
    <hr />
        <p><a href="${joinUrl}"><input type="button" value="회원가입" id="signup" /></a></p>
    </div>
</layout:bodyOnly>