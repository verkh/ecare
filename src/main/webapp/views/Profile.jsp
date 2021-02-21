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
    <meta path="user.viewport" content="width=device-width, initial-scale=1">

    <title>Registration</title>
    <link rel="icon" href="<spring:url value='/images/eCareIcon.png'/>">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- eCare CSS -->
    <link href="<spring:url value='/css/common.css'/>" rel="stylesheet">
    <link href="<spring:url value='/css/form.css'/>" rel="stylesheet">

    <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet"/>
    <script src="<spring:url value='/js/Validation.js'/>"></script>
</head>
<body>

<!-- Navigation panel -->
<jsp:include page="/views/Navbar.jsp"/>
<br/>
<br/>
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
        $(document).ready(function () {
            $('.toast').toast('show');
        });
    </script>
</c:if>
<div id=wrapper >
    <div class="d-flex justify-content-center">
        <form:form action="${current_action}" method="POST" modelAttribute="contract" style="">
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
                                <form:input path="user.name" type="text" class="form-control"
                                            id="pathInput" aria-describedby="pathHelp"
                                            placeholder="Enter name"
                                            onkeypress="return validateLetters(event)"></form:input>
                                <form:errors class="text-danger" path="user.name"/>
                                <small id="pathHelp" class="form-text text-muted">Fake information leads to jail</small>
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="lastpathInput">Last name</label>
                                <form:input path="user.lastName" type="text" class="form-control"
                                            id="lastpathInput" placeholder="Enter last name"
                                            onkeypress="return validateLetters(event)"></form:input>
                                <form:errors class="text-danger" path="user.lastName"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <div class="form-group">
                                <label for="exampleInputEmail1">Email address</label>
                                <form:input type="email" path="user.email" class="form-control"
                                            id="exampleInputEmail1" aria-describedby="emailHelp"
                                            placeholder="Enter email"></form:input>
                                <form:errors class="text-danger" path="user.email"/>
                                <small id="emailHelp" class="form-text text-muted">You're email would be used for
                                    Dictator's notifications</small>
                            </div>
                        </div>
                    </div>
                    <security:authorize access="hasRole('ADMIN')">
                        <c:set var="AdminAccess" value="true" scope="session"/>
                    </security:authorize>
                    <c:if test="${empty contract.user.id || not empty AdminAccess}">
                        <div class="row">
                            <div class="form-group">
                                <div class="row">
                                    <div class="col">
                                        <label for="phoneNumber">Phone Number</label>
                                        <c:choose>
                                            <c:when test="${empty contract.user.id}">
                                                <form:input type="tel" path="phoneNumber" class="form-control"
                                                            id="phoneNumber"
                                                            placeholder="+7 (___)-___-__-__"
                                                            data-slots="_"></form:input>
                                            </c:when>
                                            <c:otherwise>
                                                <form:input type="tel" path="phoneNumber" class="form-control"
                                                            id="phoneNumber"
                                                            placeholder="+7 (___)-___-__-__" data-slots="_"
                                                            disabled="true"
                                                            data-inputmask="'mask': '8(999)-999-99-'"></form:input>
                                            </c:otherwise>
                                        </c:choose>
                                        <form:errors class="text-danger" path="phoneNumber"/>
                                    </div>
                                    <div class="col">
                                        <label for="planSelect">Plan</label>
                                        <div class="input-group mb-3">
                                            <form:select class="form-select form-control-lg" id="planSelect"
                                                         path="plan.id">
                                                <form:options items="${availablePlans}" itemValue="id"
                                                              itemLabel="name"></form:options>
                                            </form:select>
                                            <c:if test="${not empty AdminAccess && not empty contract.user.id}">
                                                <a href="${pageContext.request.contextPath}/administration/contracts/${contract.id}"
                                                   type="button" class="btn btn-secondary">
                                                    <i class="fa fa-cog"></i>
                                                </a>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    <div class="row">
                        <div class="col">
                            <div class="form-group">
                                <label for="inputPassword">Password</label>
                                <form:input type="password" path="user.rawPassword" class="form-control"
                                            id="inputPassword"
                                            placeholder="Password"></form:input>
                                <form:errors class="text-danger" path="user.passwordHash"/>
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
                                    <form:select class="custom-select" id="roleSelect" path="user.authority">
                                        <form:options items="${avaliableAuthorities}"
                                                      itemLabel="humanReadableValue"></form:options>
                                    </form:select>
                                </div>
                            </div>
                        </div>
                    </security:authorize>
                    <div class="row">
                        <div class="col">
                            <div class="form-group">
                                <label for="birthDate">Birth Date</label>
                                <form:input value="${user.date}" type="date" path="user.date" class="form-control"
                                            id="birthDate"></form:input>
                                <form:errors class="text-danger" path="user.date"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group">
                            <label for="passport">Passport</label>
                            <form:textarea type="text" path="user.passport" class="form-control" id="passport"
                                           placeholder="Enter passport"></form:textarea>
                            <form:errors class="text-danger" path="user.passport"/>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group">
                            <label for="address">Address</label>
                            <form:textarea type="text" path="user.address" class="form-control" id="address"
                                           placeholder="Enter address"></form:textarea>
                            <form:errors class="text-danger" path="user.address"/>
                        </div>
                    </div>

                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <div class="btn-group">
                        <input type="submit" class="btn btn-primary" value="Save"/>
                        <c:if test="${not empty contract.user.id}">
                            <c:choose>
                                <c:when test="${empty blocked}">
                                    <a href="?block=1" type="button" class="btn btn-danger">Block</a>
                                </c:when>
                                <c:when test="${not empty blocked && not empty couldBeUnblocked}">
                                    <a href="?block=0" type="button" class="btn btn-success">Unblock</a>
                                </c:when>
                                <c:when test="${not empty blocked && empty couldBeUnblocked}">
                                    <button type="button" class="btn btn-secondary" disabled>Unblock</button>
                                </c:when>
                            </c:choose>
                        </c:if>
                    </div>
                </div>
            </div>
            <security:authorize access="hasRole('ADMIN')">
                <c:if test="${contract.user.getCurrentLatitude() != null && contract.user.getCurrentLongitude() != null}">
                    <div class="col col-sm-auto"
                         style="padding-right: 3px; padding-left:3px; margin-right:3px; margin-left:3px; min-width:400px;">
                        <div class="form card p-3 bg-dark">
                            <h4>Currently here:</h4>
                            <div style="width: 100%">
                                <iframe width="100%" height="400" frameborder="0" scrolling="yes" marginheight="0"
                                        marginwidth="0"
                                        src="https://maps.google.com/maps?width=100%25&amp;height=600&amp;hl=en&amp;q=${contract.user.getCurrentLongitude()},%20${contract.user.getCurrentLatitude()}+(Currently%20here)&amp;t=&amp;z=18&amp;ie=UTF8&amp;iwloc=B&amp;output=embed"></iframe>
                            </div>
                        </div>
                    </div>
                </c:if>
            </security:authorize>
        </div>
        </form:form>
        </div>
    </div>

</body>
</html>