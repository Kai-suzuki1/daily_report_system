<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>


<c:set var="actCMT" value="${FowardConst.ACT_CMT.getValue()}" />
<c:set var="cmmCrt" value="${FowardConst.CMD_CREATE.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">

    <c:param name="content">
        <h2>${report.employee.name} さんの ${report.title} にコメント</h2>
        <p>内容：</p>
        <span>${report.content}</span>

        <form method="post" action="<c:url value='?action=${actCMT}&command=${cmmCrt}' />" >
            <c:if test="${errors != null}">
                <div id="flush_error">
                    入力内容にエラーがあります。<br />
                    <c:forEach var="error" items="${errors}">
                        ・<c:out value="${error}" /><br />
                    </c:forEach>
               </div>
            </c:if>

            <label for="${AttributeConst.CMT_CONTENT.getValue()}">内容：</label>
            <textarea name="${AttributeConst.CMT_CONTENT.getValue()}" rows="10" cols="50" autofocus>${comment.content}</textarea>
            <br /><br />
            <input type="hidden" name="${AttributeConst.REP_ID.getValue()}" value="${report.id}" />
            <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
            <button type="submit">投稿</button>
            <button type="reset">リセット</button>
        </form>

    </c:param>
</c:import>