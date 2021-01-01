<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<header>
    <!-- NAVIGATION PANEL -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">
                <img src="<spring:url value='/images/eCareIcon.png'/>" alt="" width="50" height="50"/>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Plans
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/Plans">All plans</a></li>
                            <li><a class="dropdown-item" href="#">For rulers</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="#">For peasants</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Promo</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">About</a>
                    </li>
                </ul>
                <form class="d-flex">
                    <a href="${pageContext.request.contextPath}/SignIn" type="button" class="btn btn-default navbar-btn oi oi-account-login">Sign in</a>
                    <a href="${pageContext.request.contextPath}/SignUp" type="button" class="btn btn-default navbar-btn" >Sign up</a>
                </form>
            </div>
        </div>
    </nav>
</header>