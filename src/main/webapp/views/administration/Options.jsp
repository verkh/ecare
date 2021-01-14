<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>eCare Options</title>
    <link rel="icon" href="<spring:url value='/images/eCareIcon.png'/>">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <!-- eCare CSS -->
    <link href="<spring:url value='/css/common.css'/>" rel="stylesheet">
    <link href="<spring:url value='/css/form.css'/>" rel="stylesheet">
</head>

<body>
<jsp:include page="/views/Navbar.jsp"/>

<br>
<br>

<div class="container horizontal-card card p-3 bg-dark">

    <h2>eCare Plans</h2>
    <hr/>
    <br/>

    <a href="${pageContext.request.contextPath}/administration/options/new" class="btn btn-md btn-secondary" type="submit" style="max-width: 200px;">Add new</a>

    <br>
    <br>

    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Price</th>
            <th scope="col">Turn on Price</th>
            <th scope="col">Description</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        <c:forEach items="${options}" var="option">
            <tr>
                <td>${option.id}</td>
                <td>${option.name}</td>
                <td>${option.price}</td>
                <td>${option.turnOnPrice}</td>
                <td>${option.description}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/administration/options/${option.getId()}" type="button" class="btn btn-secondary btn-sm">View</a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/administration/options?delete=${option.getId()}" type="button" class="btn btn-secondary btn-sm">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </thead>
    </table>

    <nav aria-label="Navigation">
        <ul class="pagination">
            <c:if test="${currentPage != 1}">
                <li class="page-item"><a class="page-link"
                                         href="tariffs?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
                </li>
            </c:if>

            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="page-item active"><a class="page-link">
                                ${i} <span class="sr-only">(current)</span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link"
                                                 href="tariffs?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage lt noOfPages}">
                <li class="page-item"><a class="page-link"
                                         href="tariffs?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>
</hr>
<!-- Footer -->
<jsp:include page="/views/Footer.jsp"/>

</body>
</html>