<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>eCare Sign in</title>
    <link rel="icon" href="<spring:url value='/images/eCareIcon.png'/>">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <!-- eCare CSS -->
    <link href="<spring:url value='/css/common.css'/>" rel="stylesheet">
    <link href="<spring:url value='/css/signin.css'/>" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="floating-labels.css" rel="stylesheet">
</head>

<body class="text-center">
<jsp:include page="/views/Navbar.jsp"/>

<br/>
<br/>
<main class="form-signin card p-3 bg-dark">
    <form action="SignIn" method="POST">
        <div class="text-center mb-4">
            <img class="mb-4" src="<spring:url value='/images/eCareIcon.png'/>" alt="" width="100" height="100">
            <h1 class="h3 mb-3 font-weight-normal">Authentication</h1>
        </div>
        <div class="form-label-group">
            <input name="username" type="text" id="inputLogin" class="form-control" placeholder="Email or phone or username" required autofocus>
        </div>
        <br/>
        <div class="form-label-group">
            <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
        </div>
        <c:if test="${not empty error}">
            <div class="text-danger text-sm-center" role="alert">${error}</div>
        </c:if>
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <a type="button" class="btn" href="${pageContext.request.contextPath}/SignUp">Don't have an account?</a>
        <p class="mt-5 mb-3 text-muted text-center">&copy; 2020 - Until death do us part</p>
    </form>
</main>

</body>
</html>