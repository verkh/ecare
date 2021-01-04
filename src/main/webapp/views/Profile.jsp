<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>eCare user profile</title>
    <link rel="icon" href="<spring:url value='/images/eCareIcon.png'/>">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <!-- eCare CSS -->
    <link href="<spring:url value='/css/common.css'/>" rel="stylesheet">
    <link href="<spring:url value='/css/profile.css'/>" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="floating-labels.css" rel="stylesheet">
</head>

<body class="text-center">
<jsp:include page="/views/Navbar.jsp"/>

<br/>
<div id=wrapper class="container profile">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
            crossorigin="anonymous"></script>

    <form action="Profile" method="POST" class="form card p-3 bg-dark">
        <div class="text-center mb-4">
            <img class="mb-4" src="<spring:url value='/images/eCareIcon.png'/>" alt="" width="100" height="100">
            <h1 class="h3 mb-3 font-weight-normal">Profile</h1>
        </div>

        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label for="nameInput">Name</label>
                    <input type="text" name="firstName" class="form-control" id="nameInput" aria-describedby="nameHelp"
                           placeholder="Enter name" value="${user.name}">
                    <small id="nameHelp" class="form-text text-muted">Fake information leads to jail</small>
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label for="lastNameInput">Last Name</label>
                    <input type="text" name="lastName" class="form-control" id="lastNameInput"
                           placeholder="Enter last name"  value="${user.lastName}">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label for="exampleInputEmail1">Email address</label>
                    <input type="email" name="email" class="form-control" id="exampleInputEmail1"
                           aria-describedby="emailHelp" placeholder="Enter email" value="${user.email}">
                    <small id="emailHelp" class="form-text text-muted">You're email would be used for Dictator's
                        notifications</small>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label for="inputPassword">Password</label>
                    <input type="password" name="password" class="form-control" id="inputPassword"
                           placeholder="Password">
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label for="inputPasswordConfirm">Password again</label>
                    <input type="password" name="password" class="form-control" id="inputPasswordConfirm"
                           placeholder="Password again">
                </div>
            </div>
        </div>

        <!-- EXTRA INFO -->
        <hr>

        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label for="birthDate">Birth Date</label>
                    <input type="date" name="birthDate" class="form-control" id="birthDate" value="${user.date}">
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group">
                <label for="passport">Passport</label>
                <input type="text" name="passport" class="form-control" id="passport" placeholder="Enter passport" value="${user.passport}">
            </div>
        </div>

        <div class="row">
            <div class="form-group">
                <label for="address">Address</label>
                <input type="text" name="address" class="form-control" id="address" placeholder="Enter address" value="${user.address}">
            </div>
        </div>

    </form>
</div>
</body>
</html>