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

    <title>Registration finish</title>
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
    <form action="${pageContext.request.contextPath}/SignUp" method="POST" class="form card p-3 bg-dark">
        <div class="text-center mb-4">
            <img class="mb-4" src="<spring:url value='/images/eCareIcon.png'/>" alt="" width="100" height="100">
            <h1 class="h3 mb-3 font-weight-normal">One more step...</h1>
        </div>
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label for="birthDate">Birth Date</label>
                    <input type="date" name="birthDate" class="form-control" id="birthDate">
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group">
                <label for="passport">Passport</label>
                <input type="text" name="passport" class="form-control" id="passport" placeholder="Enter passport">
            </div>
        </div>

        <div class="row">
            <div class="form-group">
                <label for="address">Address</label>
                <input type="text" name="address" class="form-control" id="address" placeholder="Enter address">
            </div>
        </div>

        <input type="submit" class="btn btn-primary" value="Finish"/>
    </form>
</div>

</body>
</html>