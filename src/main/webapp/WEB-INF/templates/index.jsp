<%--직접 접근하기 위해 webapp에 만듦--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<fmt:setBundle basename="messages.commons" />
<c:url var="loginUrl" value="/member/login" />
<c:url var="joinUrl" value="/member/join" />
<c:url var="indexUrl" value="/index" />
<c:url var="logoutUrl" value="/member/logout" />
<c:url var="mypageUrl" value="/mypage" />

<layout:bodyOnly title="메인..">
    <div class="header">
        <div class="logo">
            <h1><a href="#"><font color="white">메인페이지</font></a></h1>
        </div>
        <div class="btn">
    <c:if test="${sessionScope.member == null}"> <%-- 미 로그인 상태 --%>

        <a href="${loginUrl}" class="nav-link">로그인</a>
        <a href="${joinUrl}" class="nav-link">회원가입</a>
    </c:if>
    <c:if test="${sessionScope.member != null}"> <%-- 로그인 상태 --%>
        <fmt:message key="LOGIN_MSG">
            <fmt:param>${sessionScope.member.userNm}</fmt:param>
            <fmt:param>${sessionScope.member.userId}</fmt:param>
        </fmt:message>
        <a href="${logoutUrl}" class="nav-link">로그아웃</a>
        <a href="${mypageUrl}" class="nav-link">마이페이지</a>
    </c:if>
    </div>
    </div>
</layout:bodyOnly>