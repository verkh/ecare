<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta path="viewport" content="width=device-width, initial-scale=1">

    <title>${Plan.name}</title>
    <link rel="icon" href="<spring:url value='/images/eCareIcon.png'/>">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <!-- eCare CSS -->
    <link href="<spring:url value='/css/common.css'/>" rel="stylesheet">
    <link href="<spring:url value='/css/form.css'/>" rel="stylesheet">
</head>
<body>

<!-- Navigation panel -->
<jsp:include page="/views/Navbar.jsp"/>
<br/>
<br/>
<div id=wrapper class="container">
    <div class="d-flex justify-content-center">
        <form:form action="${current_action}" method="POST" style="min-width: 600px;" modelAttribute="Plan">
            <div class="form card p-3 bg-dark">
                <div class="text-center mb-4">
                    <img class="mb-4" src="<spring:url value='/images/eCareIcon.png'/>" alt="" width="100"
                         height="100">
                    <h1 class="h3 mb-3 font-weight-normal">${current_action_title}</h1>
                </div>
                <h2>${Plan.name}</h2>
                <hr/>
                <br/>

                <div class="form-group">
                    <label for="nameInput">Name</label>
                    <form:input type="text" path="name" class="form-control"
                                id="nameInput" aria-describedby="nameHelp" placeholder="Enter name"></form:input>
                </div>

                <label for="priceInput">Price</label>
                <div class="input-group">
                    <form:input type="text" path="price" class="form-control"
                                id="priceInput" placeholder="Enter last name"></form:input>
                    <div class="input-group-append">
                        <span class="input-group-text">$</span>
                    </div>
                </div>

                <button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>
            </div>
        </form:form>
    </div>
</div>

</body>
</html>