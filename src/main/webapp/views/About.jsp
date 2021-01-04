<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>About eCare</title>
    <link rel="icon" href="<spring:url value='/images/eCareIcon.png'/>">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <!-- eCare CSS -->
    <link href="<spring:url value='/css/common.css'/>" rel="stylesheet">
    <link href="<spring:url value='/css/form.css'/>" rel="stylesheet">
</head>

<body>
<jsp:include page="/views/Navbar.jsp"/>

<div class="container horizontal-card card p-3 bg-dark">

    <br/>
    <h1>About</h1>
    <br/>
    <hr/>

    <h2>eCare is there for you</h2>
    <p>* Yes! for your a teeny-weeny cute little dictator!</p>
    <div class="row">
        <div class="col">
            <img src="<spring:url value='/images/about1.jpeg' />" class="carousel-img" alt="about1">
        </div>
        <div class="col">
            <h3>Some facts</h3>
            <p>* Our company history begins in 1983 in Borsh</p>
            <p>* In 2012 we helped Atashan to keep power while his country was fallen apart</p>
            <p>* In 2020 we founed ePandemic to spread virus and block all 5G towers</p>
        </div>
    </div>
    <hr/>

    <h2>We're helping dictator to rule!</h2>
    <div class="row">
        <div class="col">
            <h3>Some facts</h3>
            <p>* You always can escape to Borsh. We have a couch</p>
            <p>* But before you can block internet in your country</p>
            <p>* And also send some treats to your peasants</p>
        </div>
        <div class="col">
            <img src="<spring:url value='/images/about2.jpeg' />" class="carousel-img" alt="about1">
        </div>
    </div>
    <hr/>

    <h2>You always can rely on us!</h2>
    <div class="row">
        <div class="col">
            <h3>Contacts</h3>
            <p>* Hey, I just met you, and this is crazy
                 But here's my number, so call me maybe: +6 (666) 66 66 </p>
        </div>
        <div class="col">
            <img src="<spring:url value='/images/about3.jpeg' />" class="carousel-img" alt="about1">
        </div>
    </div>
    <hr/>

    <div class="container-fluid align-middle">
        <h3>You can find us: </h3>
        <p>Albania, Borsh, Grecha... no, just Borsh city.
                Find us
                You won't regret
        </p>
        <div class="mapouter"><div class="gmap_canvas"><iframe width="900" height="300" id="gmap_canvas" src="https://maps.google.com/maps?q=Borsh&t=&z=13&ie=UTF8&iwloc=&output=embed" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe><a href="https://fmovies2.org">fmovies</a></div><style>.mapouter{position:relative;text-align:right;height:300px;width:900px;}.gmap_canvas {overflow:hidden;background:none!important;height:300px;width:900px;}</style></div>
    </div>
</div>
<!-- Footer -->
<jsp:include page="/views/Footer.jsp"/>

</body>
</html>