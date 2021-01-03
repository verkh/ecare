<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <script src="<spring:url value='/js/Plans.js'/>"></script>

    <!-- eCare CSS -->
    <link href="<spring:url value='/css/common.css'/>" rel="stylesheet">
    <link href="<spring:url value='/css/form.css'/>" rel="stylesheet">
</head>

<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
<jsp:include page="/views/Navbar.jsp"/>

<div class="container">

    <br/>
    <h2>Plan for your mobile phone</h2>
    <br/>

    <jstl:forEach items="${Plans}" var="plan">
        <br>
        <div class="glow-horizontal-card card p-3 bg-dark">
            <div class="row">
                <div class="col">
                    <h2>${plan.name}</h2>
                </div>
                <div class="col">
                    <jstl:forEach items="${plan.options}" var="option">
                        <h5>${option.name}</h5>
                    </jstl:forEach>
                </div>
                <div class="col"></div>
                <div class="col">
                    <h4 class="text-center">${plan.price}$/month</h4>
                    <a href="${pageContext.request.contextPath}/Plans/${plan.id}" type="button" class="w-100 btn btn-lg btn-primary">Choose</a>
                </div>
            </div>
        </div>
        </br>
    </jstl:forEach>

</div>
<!-- Footer -->
${footer}

</body>
</html>