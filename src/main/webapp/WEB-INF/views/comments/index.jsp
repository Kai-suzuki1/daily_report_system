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
<c:set var="commDel" value="${ForwardConst.CMD_DESTROY.getValue()}" />

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
                       <td class="comment_name"><c:out value="${myComment.report.employee.name}" /></td>
                       <td class="comment_title"><c:out value="${myComment.report.title}" /></td>
                       <td class="comment_date"><fmt:formatDate value="${commentDay}" pattern='yyyy-MM-dd' /></td>
                       <td class="comment_content"><c:out value="${myComment.content}" /></td>
                       <td class="comment_action">
                           <a href="<c:url value='?action=${actCmt}&command=${commNew}&id=${myComment.report.id}' />">追加コメント<span class="far fa-eye" style="color:#24738e;"></span></a>
                           <a href="<c:url value='?action=${actCmt}&command=${commEdt}&id=${myComment.id}' />">編集<span class="far fa-edit" style="color:#24738e;"></span></a>
                           <form method="post" action="<c:url value='?action=${actCmt}&command=${commDel}' />" class="form_delete" >
                                <input type="hidden" name="${AttributeConst.CMT_ID.getValue()}" value="${myComment.id}" />
                                <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
                                <button type="submit" class="btn_delete">削除<span class="far fa-trash-alt" style="color:#24738e;"></span></button>
                           </form>
<%--                            <a href="#" onclick="confirmDestroy();">削除</a>
                           <form method="post" action="<c:url value='?action=${actCmt}&command=${commDel}' />" >
                            <input type="hidden" name="${AttributeConst.CMT_ID.getValue()}" value="${myComment.id}" />
                            <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
                           </form>
                           <script>
                          function confirmDestroy() {
                             if (confirm("本当に削除してよろしいですか？")) {
                                 document.forms[1].submit();
                               }
                              }
                        </script> --%>
                       </td>
                   </tr>
               </c:forEach>
           </tbody>
        </table>

        <div id="pagination">
            （全 ${comments_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((comments_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actCmt}&command=${commIdx}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='?action=${actRep}&command=${commIdx}' />">他の日報をみる</a></p>
    </c:param>
</c:import>