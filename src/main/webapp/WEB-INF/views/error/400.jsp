<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="commons-error.jsp" charEncoding="UTF-8">
    <c:param name="title" value="Bad Request" />
    <c:param name="errorNumber" value="400" />
    <c:param name="errorMessage" value="서버가 요청의 구문을 인식하지 못했습닌다." />
    <c:param name="timeout" value="${timeout}" />
    <c:param name="url" value="${url}" />
</c:import>

