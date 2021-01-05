<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

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
        <form action="${current_action}" method="POST">
            <div class="row">
                <div class="col col-sm-auto" style="padding-right: 3px; padding-left:3px; margin-right:3px; margin-left:3px">
                    <div class="form card p-3 bg-dark">
                        <div class="text-center mb-4">
                            <img class="mb-4" src="<spring:url value='/images/eCareIcon.png'/>" alt="" width="100" height="100">
                            <h1 class="h3 mb-3 font-weight-normal">${current_action_title}</h1>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="nameInput">Name</label>
                                    <input value="${user.name}" type="text" name="firstName" class="form-control" id="nameInput" aria-describedby="nameHelp" placeholder="Enter name">
                                    <small id="nameHelp" class="form-text text-muted">Fake information leads to jail</small>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="lastNameInput">Last Name</label>
                                    <input value="${user.lastName}" type="text" name="lastName" class="form-control" id="lastNameInput" placeholder="Enter last name">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Email address</label>
                                    <input value="${user.email}" type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
                                    <small id="emailHelp" class="form-text text-muted">You're email would be used for Dictator's notifications</small>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group">
                                <label for="phoneNumber">Phone Number</label>
                                <input type="tel" name="phoneNumber" class="form-control" id="phoneNumber" placeholder="Enter phone number">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="inputPassword">Password</label>
                                    <input type="password" name="password" class="form-control" id="inputPassword" placeholder="Password">
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="inputPasswordConfirm">Password again</label>
                                    <input type="password" name="password" class="form-control" id="inputPasswordConfirm" placeholder="Password again">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col col-sm-auto" style="padding-right: 3px; padding-left:3px; margin-right:3px; margin-left:3px; min-width:400px;">
                    <div class="form card p-3 bg-dark">
                        <security:authorize access="hasRole('ADMIN')">
                            <div class="row">
                                <div class="col">
                                    <div class="form-group">
                                        <label for="birthDate">User role</label>
                                        <select class="custom-select" id="inputGroupSelect01">
                                            <option selected>Choose...</option>
                                            <option value="USER">User</option>
                                            <option value="DICTATOR">Dictator</option>
                                            <option value="ADMIN">Admin</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </security:authorize>
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="birthDate">Birth Date</label>
                                    <input value="${user.date}" type="date" name="birthDate" class="form-control" id="birthDate">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group">
                                <label for="passport">Passport</label>
                                <textarea type="text" name="passport" class="form-control" id="passport" placeholder="Enter passport">${user.passport}</textarea>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group">
                                <label for="address">Address</label>
                                <textarea type="text" name="address" class="form-control" id="address" placeholder="Enter address">${user.address}</textarea>
                            </div>
                        </div>

                        <c:if test="${not empty error}">
                            <div class="text-danger text-sm-center" role="alert">${error}</div>
                        </c:if>
                        <input type="hidden"
                               name="${_csrf.parameterName}"
                               value="${_csrf.token}"/>
                        <input type="submit" class="btn btn-primary" value="Sign up"/>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

</body>
</html>