<%@ tag body-content="scriptless" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ attribute name="title" %>
<%@ attribute name="header" fragment="true" %>
<%@ attribute name="footer" fragment="true" %>
<c:url var="cssUrl" value="/static/css/" />
<c:url var="jsUrl" value="/static/js/" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>${title}</title>
        <link rel="stylesheet" type="text/css" href="${cssUrl}style.css" />
        <c:if test="${addCss != null}">
            <c:forEach var="path" items="${addCss}">
            <link rel="stylesheet" type="text/css" href="${cssUrl}${path}.css">
            </c:forEach>
        </c:if>

        <script src="${commonJs}"></script>
        <c:if test="${addScript != null}">
            <c:forEach var="path" items="${addScript}">
                <script src="${jsUrl}${path}.js"></script>
            </c:forEach>
        </c:if>
    </head>
    <body>

        <jsp:invoke fragment="header" />

        <main>
            <jsp:doBody />
        </main>

        <jsp:invoke fragment="footer" />

        <iframe name="ifrmProcess" class="dn"></iframe>
    </body>
</html>