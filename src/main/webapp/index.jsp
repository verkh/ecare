<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>eCare</title>
    <link rel="icon" href="<spring:url value='/images/eCareIcon.png'/>">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <!-- eCare CSS -->
    <link href="css/common.css" rel="stylesheet">
    <spring:url value="/css/common.css" var="common.css"/>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>

<!-- Navigation panel -->
<jsp:include page="/views/Navbar.jsp"/>

<div id=wrapper class="container form card p-3 bg-dark">
    <!-- Bootstrap JS -->

    <div class="row">
    <!-- SLIDE SHOW -->
    <div id="carouselExampleDark" class="carousel carousel-dark slide" data-bs-ride="carousel">
        <ol class="carousel-indicators">
            <li data-bs-target="#carouselExampleDark" data-bs-slide-to="0" class="active"></li>
            <li data-bs-target="#carouselExampleDark" data-bs-slide-to="1"></li>
        </ol>

        <div class="carousel-inner">
            <div class="carousel-item active" data-bs-interval="10000">
                <img src="<spring:url value='/images/dictator.png' />" class="carousel-img" alt="Dictator plan">
            </div>
            <div class="carousel-item" data-bs-interval="2000">
                <img src="<spring:url value='/images/exile.png' />" class="carousel-img" alt="Exile plan">
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleDark" role="button" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleDark" role="button" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </a>
    </div>

    </div>

    <div class="text-center card-body">
        <h1 class="display-4">Choose your country destiny</h1>
        <p class="lead">eCare helps to rule and watch for everyone in com web</p>
    </div>

    <br/>
    <div class="row">
        <div class="col">
            <div class="card-white">
                <div class="row no-gutters">
                    <div class="col">
                        <img src="<spring:url value='/images/Mornings.png'/>" class="img-fluid" alt="">
                    </div>
                    <div class="col">
                        <div class="card-block px-2">
                            <h4 class="card-title">Everyday mornings</h4>
                            <br/>
                            <p class="card-text">Imagine your ruler expresses their feeling with you every morning!</p>
                            <br/>
                            <h1 class="card-title pricing-card-title">$1000 <small class="text-muted">/ mo</small></h1>
                            <br/>
                            <p class="card-text">Imagine your ruler expresses their feeling with you every morning!</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="container-fluid">
                <div class="sizer">
                    <video class="video-fluid z-depth-1" autoplay loop controls muted>
                        <source src="<spring:url value='/videos/eCarePromo.mp4'/>" type="video/mp4" />
                    </video>
                </div>
            </div>
        </div>
    </div>
    <br/>

    <!-- PLANS CARDS -->

    <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
        <h1 class="display-4">Plans for everyone</h1>
        <p class="lead">Take them now!</p>
    </div>

    <div class="row row-cols-1 row-cols-md-3 mb-3 text-center">
        <div class="col">
            <div class="card mb-4 shadow-sm">
                <div class="card-header">
                    <h4 class="my-0 fw-normal">Exile</h4>
                </div>
                <div class="card-body">
                    <h1 class="card-title pricing-card-title">$50 <small class="text-muted">/ mo</small></h1>
                    <ul class="list-unstyled mt-3 mb-4">
                        <li>Call nobody</li>
                        <li>No internet</li>
                        <li>Infinite suffer</li>
                        <li>Infinite spam</li>
                    </ul>
                    <button type="button" class="w-100 btn btn-lg btn-primary">Get started</button>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card mb-4 shadow-sm">
                <div class="card-header">
                    <h4 class="my-0 fw-normal">Peasant</h4>
                </div>
                <div class="card-body">
                    <h1 class="card-title pricing-card-title">$2 <small class="text-muted">/ mo</small></h1>
                    <ul class="list-unstyled mt-3 mb-4">
                        <li>30 minutes</li>
                        <li>1 GB</li>
                        <li>Unlimited access to propaganda</li>
                        <li>Morning greetings from Dictator</li>
                    </ul>
                    <button type="button" class="w-100 btn btn-lg btn-primary">Get started</button>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card mb-4 shadow-sm">
                <div class="card-header">
                    <h4 class="my-0 fw-normal">Dictator</h4>
                </div>
                <div class="card-body">
                    <h1 class="card-title pricing-card-title">$1000 <small class="text-muted">/ mo</small></h1>
                    <ul class="list-unstyled mt-3 mb-4">
                        <li>Unlimited calls and Internet</li>
                        <li>15 GB of storage</li>
                        <li>Spy on everyone</li>
                        <li>Anytime block  all communication Country</li>
                    </ul>
                    <button type="button" class="w-100 btn btn-lg btn-primary">Get started</button>
                </div>
            </div>
        </div>

        <br/>

    </div>

    <!-- Footer -->
    <jsp:include page="views/Footer.jsp"/>

</div>
</body>
</html>
