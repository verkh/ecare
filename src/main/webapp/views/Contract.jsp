<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    <!-- eCare CSS -->
    <link href="<spring:url value='/css/common.css'/>" rel="stylesheet">
    <link href="<spring:url value='/css/form.css'/>" rel="stylesheet">
</head>

<body>

<jsp:include page="/views/Navbar.jsp"/>

<br>
<br>

<c:if test="${not empty success}">
    <div class="toast" data-autohide="false" style="position: absolute; top: 80px; left: 0px;">
        <div class="toast-header text-success">
            <strong class="mr-auto">Done!</strong>
        </div>
        <div class="toast-body">
                ${success}
        </div>
    </div>
    <script>
        $(document).ready(function(){
            $('.toast').toast('show');
        });
    </script>
</c:if>

<div class="container horizontal-card card p-3 bg-dark">

    <h2>eCare ${contract.plan.name}</h2>
    <h6>for ${contract.user.name}. That's right, dude!</h6>
    <hr/>
    <br/>
    <h4><b>Number:</b> ${contract.phoneNumber}</h4>
    <br/>
    <br/>

    <h4>Available options</h4>
    <form:form action="${current_action}" method="POST" style="min-width: 600px;" modelAttribute="optionsContract">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Description</th>
                <th scope="col">Price</th>
                <th scope="col">Turn on price</th>
                <th scope="col"></th>
            </tr>
            <c:forEach items="${optionsContract.options}" var="option" varStatus="status">
                <tr>
                    <td>${option.name}
                        <p>
                        <form:errors class="text-danger" path="options[${status.index}]"/>
                    </p></td>
                    <td>${option.description}</td>
                    <td>${option.price}$</td>
                    <td>${option.turnOnPrice}$</td>
                    <td>
                        <form:checkbox path="options[${status.index}].enabled" class="form-check-input"></form:checkbox>
                    </td>
                </tr>
            </c:forEach>
            </thead>
        </table>
        <button class="btn btn-md btn-primary" type="submit" style="max-width: 200px;">Save</button>
        <a class="btn btn-md btn-secondary" href="${pageContext.request.contextPath}/plans" style="max-width: 200px;">Change plan</a>
    </form:form>
</div>
</hr>
</body>
</html>