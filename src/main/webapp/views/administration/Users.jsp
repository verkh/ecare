<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>eCare users</title>
    <link rel="icon" href="<spring:url value='/images/eCareIcon.png'/>">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- eCare CSS -->
    <link href="<spring:url value='/css/common.css'/>" rel="stylesheet">
    <link href="<spring:url value='/css/form.css'/>" rel="stylesheet">

    <script src="<spring:url value='/js/Users.js'/>"></script>
</head>

<body>

<jsp:include page="/views/Navbar.jsp"/>

<br>
<br>

<div class="container horizontal-card card p-3 bg-dark">
    <form:form action="${current_action}" method="POST">
    <h2>eCare Users</h2>
        <button class="btn btn-secondary" type="submit" name="block_all" title="Blocks everyone except admins and dictators" style="max-width: 100px;">Block all</button>
        <button class="btn btn-secondary" type="submit" name="unblock_all" title="Unblock everyone" style="max-width: 100px;">Unblock all</button>
    <hr/>
    <br/>

    <div class="input-group mb-3">

        <input type="text" class="form-control" id="search" placeholder="Enter phone or email" onchange="modifyText()" value="${searchText}"></input>
        <a href="?search" type="button" id="doSearch" class="btn btn-secondary">
            <i class="fa fa-search"></i>
        </a>
        <a href="${pageContext.request.contextPath}/administration/users" id="clearSearch" type="button" class="btn btn-secondary">
            <i class="fa fa-window-close"></i>
        </a>
    </div>

    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Passport</th>
            <th scope="col">Birth Date</th>
            <th scope="col">Address</th>
            <th scope="col">Email</th>
            <th scope="col">Phone</th>
            <th scope="col"></th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.getId()}</td>
                <td>${user.getName()}</td>
                <td>${user.getLastName()}</td>
                <td>${user.getPassport()}</td>
                <td>${user.getDate()}</td>
                <td>${user.getAddress()}</td>
                <td>${user.getEmail()}</td>
                <td>${user.contract.getPhoneNumber()}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/administration/users/${user.getId()}" type="button" class="btn btn-secondary btn-sm">View</a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/administration/contracts/${user.contract.getId()}" type="button" class="btn btn-secondary btn-sm">Contract</a>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${user.blocked==false}">
                                      <a href="${pageContext.request.contextPath}/administration/users?block=1&user_id=${user.getId()}" type="button" class="btn btn-danger btn-sm">Block</a>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/administration/users?block=0&user_id=${user.getId()}" type="button" class="btn btn-success btn-sm">Unblock</a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </thead>
    </table>

    <nav aria-label="Navigation">
        <ul class="pagination">
            <c:if test="${currentPage != 1}">
                <li class="page-item"><a class="page-link"
                                         href="users?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
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
                                                 href="users?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage lt noOfPages}">
                <li class="page-item"><a class="page-link"
                                         href="users?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>
    </form:form>
</div>
</hr>
<!-- Footer -->
<jsp:include page="/views/Footer.jsp"/>
</body>
</html>