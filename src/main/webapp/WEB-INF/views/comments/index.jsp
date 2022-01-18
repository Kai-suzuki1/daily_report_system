<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst"%>
<%@ page import="constants.AttributeConst" %>

<c:set var="actRep" value="${ForwardConst.ACT_REP.getValue()}" />
<c:set var="actLik" value="${ForwardConst.ACT_LIK.getValue()}" />
<c:set var="actCmt" value="${ForwardConst.ACT_CMT.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
          <c:when test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
          </c:when>
        </c:choose>

        <h2>コメント一覧</h2>
        <table class="comment_list">
            <tbody>
                <tr>
                    <th>日報作成者</th>
                    <th>日報タイトル</th>
                    <th>コメントした日付</th>
                    <th>コメント内容</th>
                    <th>操作</th>
                </tr>

                <c:forEach var="myComment" items="${my_comments}" varStatus="status">
                    <fmt:parseDate value="${myComment.commentDate }" pattern="yyyy-MM-dd" var="commentDay" type="date" />

                    <tr class="row${status.count % 2}">
                        <td class="comment_name"><c:out value="${myComment.employee.name}" /></td>
                        <td class="comment_title"><c:out value="${myComment.report.title}" /></td>
                        <td class="comment_date"><fmt:formatDate value="${commentDay}" pattern='yyyy-MM-dd' /></td>
                        <td class="comment_content"><c:out value="${myComment.content}" /></td>
                        <td>
                            <a href="<c:url value='?action=${actCmt}&command=${commEdt}&id=${myComment.id}' />">編集</a>
                            <span>削除</span>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </c:param>
</c:import>