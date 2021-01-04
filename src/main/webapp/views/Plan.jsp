<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="<spring:url value='/images/eCareIcon.png'/>">

    <title>eCare plans</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

    <!-- eCare CSS -->
    <link href="<spring:url value='/css/common.css'/>" rel="stylesheet">
    <link href="<spring:url value='/css/form.css'/>" rel="stylesheet">
</head>

<body>

<jsp:include page="/views/Navbar.jsp"/>

<br/>
<div class="container horizontal-card card p-3 bg-dark">
    <div class="row">
        <div class="col">
            <h1>${Plan.name}</h1>
        </div>
        <div class="col-md-auto">
            <h4 class="text-center">${Plan.price}$/month</h4>
            <a href="#" type="button" class="w-100 btn btn-sm btn-primary">Apply</a>
        </div>
    </div>
</div>
<br/>

<div class="container horizontal-card card p-3 bg-dark">
    <h2>Available options</h2>
    <hr/>
    <jstl:forEach items="${Plan.options}" var="option">
        <br>
        <div>
            <div class="row">
                <div class="col">
                    <h4>${option.name}</h4>
                </div>
                <div class="col">
                    <p>${option.description}</p>
                </div>
                <div class="col-md-auto">
                    <h4>${option.price} $</h4>
                </div>
            </div>
        </div>
        <hr/>
        </br>
    </jstl:forEach>

</div>
<!-- Footer -->
<jsp:include page="/views/Footer.jsp"/>

</body>
</html>