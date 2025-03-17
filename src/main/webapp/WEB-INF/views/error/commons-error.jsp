<%@ page trimDirectiveWhitespaces="true" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${param.title}</title>
    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/dist/css/bootstrap.min.css" />" />
    <link rel="stylesheet" href="<c:url value="/error/custom.min.css" />" />
    <c:if test="${timeout ne null}">
        <meta http-equiv='refresh' content='${timeout}; url=<c:out value="${url}" />'></meta>
    </c:if>
</head>
<body>
<div class="container body">
    <div class="main_container">
        <!-- page content -->
        <div class="col-md-12">
            <div class="col-middle">
                <div class="text-center">
                    <h1 class="error-number">${param.errorNumber}</h1>
                    <h2>${param.errorMessage}</h2>
                </div>
            </div>
        </div>
        <!-- /page content -->
    </div>
</div>
<script src="<c:url value="/webjars/bootstrap/dist/js/bootstrap.min.js" />"></script>
</body>
</html>
