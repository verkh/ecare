<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Plans">Plans</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Promo</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">About</a>
                    </li>
                </ul>
                <form class="d-flex">
                    <security:authorize var="loggedIn" access="isAuthenticated()" />
                    <c:choose>
                        <c:when test="${loggedIn}">
                            <div class="dropdown">
                                <a class="btn btn-secondary dropdown-toggle" href="#" id="userDropdown" role="button"
                                   data-bs-toggle="dropdown" aria-expanded="false">
                                    <%= request.getUserPrincipal().getName() %>
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="userProfilenavbarDropdown">
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/Profile">Profile</a></li>
                                    <li>
                                        <hr class="dropdown-divider">
                                    </li>
                                    <form name='logoutForm' action='${pageContext.request.contextPath}/logout' method='POST'>
                                        <input name="${_csrf.parameterName}" type="hidden"
                                        value="${_csrf.token}"/>
                                        <a class="dropdown-item" href="${pageContext.request.contextPath}/logout?${_csrf.parameterName}=${_csrf.token}">Log out</a>
                                    </form>
                                </ul>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/SignIn" type="button" class="btn btn-default navbar-btn oi oi-account-login">Sign in</a>
                            <a href="${pageContext.request.contextPath}/SignUp" type="button" class="btn btn-default navbar-btn" >Sign up</a>
                        </c:otherwise>
                    </c:choose>
                </form>
            </div>
        </div>
    </nav>
</header>