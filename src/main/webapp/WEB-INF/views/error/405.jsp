<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="commons-error.jsp" charEncoding="UTF-8">
    <c:param name="title" value="Method Not Allowed" />
    <c:param name="errorNumber" value="405" />
    <c:param name="errorMessage" value="지원하지 않는 Method입니다." />
    <c:param name="timeout" value="${timeout}" />
    <c:param name="url" value="${url}" />
</c:import>
