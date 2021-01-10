<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta path="viewport" content="width=device-width, initial-scale=1">

    <title>Registration</title>
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
        <form:form action="${current_action}" method="POST" modelAttribute="user">
            <div class="row">
                <div class="col col-sm-auto"
                     style="padding-right: 3px; padding-left:3px; margin-right:3px; margin-left:3px">
                    <div class="form card p-3 bg-dark">
                        <div class="text-center mb-4">
                            <img class="mb-4" src="<spring:url value='/images/eCareIcon.png'/>" alt="" width="100"
                                 height="100">
                            <h1 class="h3 mb-3 font-weight-normal">${current_action_title}</h1>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="pathInput">Name</label>
                                    <form:input path="name" type="text" class="form-control"
                                                id="pathInput" aria-describedby="pathHelp" placeholder="Enter name"></form:input>
                                    <small id="pathHelp" class="form-text text-muted">Fake information leads to jail</small>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="lastpathInput">Last path</label>
                                    <form:input path="lastName" type="text" class="form-control"
                                                id="lastpathInput" placeholder="Enter last name"></form:input>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Email address</label>
                                    <form:input value="" type="email" path="email" class="form-control"
                                           id="exampleInputEmail1" aria-describedby="emailHelp"
                                                placeholder="Enter email"></form:input>
                                    <small id="emailHelp" class="form-text text-muted">You're email would be used for
                                        Dictator's notifications</small>
                                </div>
                            </div>
                        </div>
                        <c:if test="${empty user.id}">
                            <div class="row">
                                <div class="form-group">
                                    <label for="phoneNumber">Phone Number</label>
                                    <form:input type="tel" path="contract.phoneNumber" class="form-control" id="phoneNumber"
                                                placeholder="Enter phone number"></form:input>
                                </div>
                            </div>
                        </c:if>
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="inputPassword">Password</label>
                                    <form:input type="password" path="passwordHash" class="form-control" id="inputPassword"
                                                placeholder="Password"></form:input>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col col-sm-auto"
                     style="padding-right: 3px; padding-left:3px; margin-right:3px; margin-left:3px; min-width:400px;">
                    <div class="form card p-3 bg-dark">
                        <security:authorize access="hasRole('ADMIN')">
                            <div class="row">
                                <div class="col">
                                    <div class="form-group">
                                        <label for="roleSelect">User role</label>
                                        <form:select class="custom-select" id="roleSelect" path="authority">
                                            <form:options items="${avaliableAuthorities}" itemLabel="humanReadableValue"></form:options>
                                        </form:select>
                                    </div>
                                </div>
                            </div>
                        </security:authorize>
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="birthDate">Birth Date</label>
                                    <form:input value="${user.date}" type="date" path="date" class="form-control"
                                                id="birthDate"></form:input>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group">
                                <label for="passport">Passport</label>
                                <form:textarea type="text" path="passport" class="form-control" id="passport"
                                          placeholder="Enter passport"></form:textarea>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group">
                                <label for="address">Address</label>
                                <form:textarea type="text" path="address" class="form-control" id="address"
                                          placeholder="Enter address"></form:textarea>
                            </div>
                        </div>

                        <c:if test="${not empty error}">
                            <div class="text-danger text-sm-center" role="alert">${error}</div>
                        </c:if>
                        <input type="hidden"
                               name="${_csrf.parameterName}"
                               value="${_csrf.token}"/>
                        <input type="submit" class="btn btn-primary" value="Save"/>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>

</body>
</html>