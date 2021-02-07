<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

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
                    <security:authorize access="hasRole('DICTATOR') || hasRole('ADMIN')">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="adminNavbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Administration
                            </a>
                            <div class="dropdown-menu" aria-labelledby="adminNavbarDropdownItem">
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/administration/users">Users</a>
                                <security:authorize access="hasRole('ADMIN')">
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/register">Register subscriber</a>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/administration/tariffs">Manage plans</a>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/administration/options">Manage options</a>
                                </security:authorize>
                            </div>
                        </li>
                    </security:authorize>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/plans">Plans</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/about">About</a>
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
                                    <c:if test="${userCart != null && !userCart.isContractCommited()}">
                                        <span class="dot">${userCart.getChangedOptions().size()}</span>
                                    </c:if>
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="userProfilenavbarDropdown">
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/profile">Profile</a></li>
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/contract">Contract
                                        <c:if test="${userCart != null && !userCart.isContractCommited()}">
                                            <span class="dot"> ${userCart.getChangedOptions().size()}</span>
                                        </c:if>
                                    </a></li>
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
                            <a href="${pageContext.request.contextPath}/auth" type="button" class="btn btn-default navbar-btn oi oi-account-login">Sign in</a>
                            <a href="${pageContext.request.contextPath}/register" type="button" class="btn btn-default navbar-btn" >Sign up</a>
                        </c:otherwise>
                    </c:choose>
                </form>
            </div>
        </div>
    </nav>
</header>