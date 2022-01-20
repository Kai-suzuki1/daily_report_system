<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="constants.ForwardConst"%>
<%@ page import="constants.AttributeConst" %>

<c:set var="actRep" value="${ForwardConst.ACT_REP.getValue()}" />
<c:set var="actCmt" value="${ForwardConst.ACT_CMT.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commUpd" value="${ForwardConst.CMD_UPDATE.getValue()}" />


<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>コメント　編集ページ</h2>

        <form method="post" action="<c:url value='?action=${actCmt}&command=${commUpd}' />" >
            <c:if test="${errors != null}">
                <div id="flush_error">
                    入力内容にエラーがあります。<br />
                    <c:forEach var="error" items="${errors}">
                        ・<c:out value="${error}" /><br />
                    </c:forEach>
                </div>
            </c:if>
            <ul class="comment_edit_ul">
               <li><h3>日付：<br /></h3>
                   <fmt:parseDate value="${comment.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                   <b><fmt:formatDate value="${createDay}" pattern="yyyy-MM-dd HH:mm:ss" /></b>
                </li>
                <li><h3>氏名：<br /></h3>
                  <b>${comment.employee.name}</b>
                </li>
                <li><h3><label for="${AttributeConst.CMT_CONTENT.getValue()}">コメント内容：<br /></label></h3>
                    <textarea name="${AttributeConst.CMT_CONTENT.getValue()}" rows="10" cols="50" autofocus>${comment.content}</textarea>
                    <br /><br />
                </li>
            </ul>

            <input type="hidden" name="${AttributeConst.CMT_ID.getValue()}" value="${comment.id}" />
            <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
            <button type="submit">投稿</button>
            <button type="reset">リセット</button>
        </form>

        <p>
            <a href="<c:url value='?action=${actRep}&command=${commIdx}' />">一覧に戻る</a>
        </p>

    </c:param>
</c:import>