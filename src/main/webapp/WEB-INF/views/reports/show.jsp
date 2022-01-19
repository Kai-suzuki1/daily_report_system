<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <h2>日報 詳細ページ</h2>

        <table>
            <tbody>
                <tr>
                    <th>氏名</th>
                    <td><c:out value="${report.employee.name}" /></td>
                </tr>
                <tr>
                    <th>日付</th>
                    <fmt:parseDate value="${report.reportDate}" pattern="yyyy-MM-dd"
                        var="reportDay" type="date" />
                    <td><fmt:formatDate value="${reportDay}" pattern="yyyy-MM-dd" /></td>
                </tr>
                <tr>
                    <th>内容</th>
                    <td><pre>
                            <c:out value="${report.content}" />
                        </pre></td>
                </tr>
                <tr>
                    <th>登録日時</th>
                    <fmt:parseDate value="${report.createdAt}"
                        pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                    <td><fmt:formatDate value="${createDay}"
                            pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                <tr>
                    <th>更新日時</th>
                    <fmt:parseDate value="${report.updatedAt}"
                        pattern="yyyy-MM-dd'T'HH:mm:ss" var="updateDay" type="date" />
                    <td><fmt:formatDate value="${updateDay}"
                            pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
            </tbody>
        </table>

        <c:if test="${sessionScope.login_employee.id == report.employee.id}">
            <p>
                <a href="<c:url value='?action=${actRep}&command=${commEdt}&id=${report.id}' />">この日報を編集する</a>
            </p>
        </c:if>
        <p>
            <a href="<c:url value='?action=${actRep}&command=${commIdx}' />">一覧に戻る</a>
        </p>

        <c:choose>
            <c:when test="${likes_check >= 1 || sessionScope.login_employee.id == report.employee.id}">
                  <p>
                      <a href="<c:url value='?action=${actCmt}&command=${commNew}&id=${report.id}' />" >コメント<span class="fas fa-comments" style="color:#24738e;"></span></a>
                  </p>
                  <button type="button" class="btn_done" disabled="disabled">
                      <span class="fas fa-thumbs-up"> ${likes_count} いいね！</span>
                  </button>
            </c:when>
            <c:otherwise>
                <p>
                    <a href="<c:url value='?action=${actCmt}&command=${commNew}&id=${report.id}' />" >コメント<span class="fas fa-comments" style="color:#24738e;"></span></a>
                </p>
                <form method="POST" action="<c:url value='?action=${actLik}&command=${commCrt}' />">
                    <input type="hidden" name="${AttributeConst.REP_ID.getValue()}" value="${report.id}" />
                    <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
                        <button type="submit" class="btn_Nyet">
                            <span class="far fa-thumbs-up"> ${likes_count} いいね！</span>
                        </button>
                </form>
            </c:otherwise>
        </c:choose>
    </c:param>
</c:import>