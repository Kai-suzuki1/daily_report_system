<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="actRep" value="${ForwardConst.ACT_REP.getValue()}" />
<c:set var="actCmt" value="${ForwardConst.ACT_CMT.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="cmmCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">

    <c:param name="content">
        <h2>${report.employee.name} さんの ${report.title} にコメント</h2>
        <h3>日報内容：</h3>
        <p class="root_comment">${report.content}</p>

        <c:forEach var="commentEach" items="${comments_on_report}">
            <fmt:parseDate value="${commentEach.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
            <fmt:parseDate value="${commentEach.updatedAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="updateDay" type="date" />

            <div class="comments_detail">
                <ul class="comment_new_ul">
                    <li>名前：<c:out value="${commentEach.employee.name}" /></li>
                    <li>日付：<fmt:formatDate value="${createDay}" pattern="yyyy-MM-dd HH:mm:ss" /></li>
                    <li>
                        <c:if test="${sessionScope.login_employee.id == commentEach.employee.id}">
                            <a href="<c:url value='?action=${actCmt}&command=${commEdt}&id=${commentEach.id}' />">編集する</a>
                        </c:if>
                    </li>
                    <li>
                        <c:if test="${commentEach.createdAt != commentEach.updatedAt}">
                           <span>(編集済み)</span>
                        </c:if>
                    </li>
                    <li><br /><span class="fas fa-comment-dots">&nbsp;<c:out value="${commentEach.content}" /></span></li>
                </ul>
            </div>
        </c:forEach>

        <form method="post" action="<c:url value='?action=${actCmt}&command=${cmmCrt}' />" >
            <c:if test="${errors != null}">
                <div id="flush_error">
                    入力内容にエラーがあります。<br />
                    <c:forEach var="error" items="${errors}">
                        ・<c:out value="${error}" /><br />
                    </c:forEach>
               </div>
            </c:if>

            <br />
            <h3>
                <label for="${AttributeConst.CMT_CONTENT.getValue()}">コメント：</label><br />
            </h3>
            <textarea name="${AttributeConst.CMT_CONTENT.getValue()}" rows="10" cols="50" placeholder="読んだ日報にコメントしよう！" autofocus>${comment.content}</textarea>
            <br /><br />
            <input type="hidden" name="${AttributeConst.REP_ID.getValue()}" value="${report.id}" />
            <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
            <button type="submit">投稿</button>
            <button type="reset">リセット</button>
        </form>

       <p>
            <a href="<c:url value='?action=${actRep}&command=${commIdx}' />">一覧に戻る</a>
        </p>

    </c:param>
</c:import>