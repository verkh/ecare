<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>

<!-- Navigation panel -->
<jsp:include page="/views/Navbar.jsp"/>
<br/>
<br/>
<div id=wrapper class="container">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
            crossorigin="anonymous"></script>


    <form action="SignUp" method="POST" class="form card p-3 bg-dark">
        <div class="text-center mb-4">
            <img class="mb-4" src="<spring:url value='/images/eCareIcon.png'/>" alt="" width="100" height="100">
            <h1 class="h3 mb-3 font-weight-normal">Registration</h1>
        </div>
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label for="nameInput">Name</label>
                    <input type="text" name="firstName" class="form-control" id="nameInput" aria-describedby="nameHelp" placeholder="Enter name">
                    <small id="nameHelp" class="form-text text-muted">Fake information leads to jail</small>
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label for="lastNameInput">Last Name</label>
                    <input type="text" name="lastName" class="form-control" id="lastNameInput" placeholder="Enter last name">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label for="exampleInputEmail1">Email address</label>
                    <input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
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
        <input type="submit" class="btn btn-primary"/>
    </form>
</div>

</body>
</html>