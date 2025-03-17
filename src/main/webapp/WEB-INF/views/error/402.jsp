<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="commons-error.jsp" charEncoding="UTF-8">
    <c:param name="title" value="Payment Required" />
    <c:param name="errorNumber" value="402" />
    <c:param name="errorMessage" value="이 요청은 결제가 필요합니다." />
    <c:param name="timeout" value="${timeout}" />
    <c:param name="url" value="${url}" />
</c:import>

