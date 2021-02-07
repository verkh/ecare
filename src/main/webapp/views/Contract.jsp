<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>eCare users</title>
    <link rel="icon" href="<spring:url value='/images/eCareIcon.png'/>">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <!-- eCare CSS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <link href="<spring:url value='/css/common.css'/>" rel="stylesheet">
    <link href="<spring:url value='/css/form.css'/>" rel="stylesheet">
</head>

<body>

<jsp:include page="/views/Navbar.jsp"/>

<br>
<br>

<c:if test="${not empty success}">
    <div class="toast" data-autohide="false" style="position: absolute; top: 80px; left: 0px;">
        <div class="toast-header text-success">
            <strong class="mr-auto">Done!</strong>
        </div>
        <div class="toast-body">
                ${success}
        </div>
    </div>
    <script>
        $(document).ready(function () {
            $('.toast').toast('show');
        });
    </script>
</c:if>

<div class="container horizontal-card card p-3 bg-dark">

    <h2>eCare ${contract.plan.name}</h2>
    <h6>for ${contract.user.name}. That's right, dude!</h6>
    <hr/>
    <br/>
    <h4><b>Number:</b> ${contract.phoneNumber}</h4>
    <br/>
    <br/>

    <h4>Available options</h4>
    <form:form id="contract_form" action="${current_action}" method="POST" style="min-width: 600px;" modelAttribute="userCart">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Description</th>
                <th scope="col">Price</th>
                <th scope="col">Turn on price</th>
                <th scope="col"></th>
            </tr>
            <c:forEach items="${userCart.newContract.options}" var="option" varStatus="status">
                <tr>
                    <td>${option.name}
                        <p>
                            <form:errors class="text-danger" path="newContract.options[${status.index}]"/>
                        </p></td>
                    <td>${option.description}</td>
                    <td>${option.price}$</td>
                    <td>${option.turnOnPrice}$</td>
                    <td>
                        <c:choose>
                            <c:when test="${option.undisablable}">
                                <form:checkbox onchange="updateCart(this)" path="newContract.options[${status.index}].enabled"
                                               id="option_cb_${option.id}"
                                               class="form-check-input" disabled="true"
                                               style="pointer-events: none;"></form:checkbox>
                            </c:when>
                            <c:otherwise>
                                <form:checkbox onchange="updateCart(this)" path="newContract.options[${status.index}].enabled"
                                               id="option_cb_${option.id}"
                                               class="form-check-input"></form:checkbox>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </thead>
        </table>
        <button id="btn_save" class="btn btn-md btn-primary" type="submit" style="max-width: 200px;" name="save">Save</button>
        <button id="btn_discard" class="btn btn-md btn-primary" type="submit" style="max-width: 200px;" name="discard">Discard</button>
        <a id="btn_change_plan" class="btn btn-md btn-secondary" href="${pageContext.request.contextPath}/plans" style="max-width: 200px;">Change
            plan</a>
        <script>
            function updateCart(checkbox) {
                checkbox.form.submit();
            }

            function process() {
                let template="option_cb_";
                let elements = document.querySelectorAll("[id*=option_cb_]");

                let jsonData = eval('('+'${userCart.getChangedOptionsIds()}'+')');
                let data = jsonData.changedOptionIds;

                for (let i = 0, length = elements.length; i < length; i++) {
                    let id = parseInt(elements[i].id.substring(template.length));
                    let changed = data.includes(id);
                    elements[i].style.boxShadow = changed ? "0px 0px 12px #d66834" : "";
                }

                let blocked = eval('('+'${userCart.originalContract.user.blocked}'+')');

                document.getElementById("btn_discard").disabled = (data.length == 0);
                document.getElementById("btn_save").disabled = (data.length == 0);
                document.getElementById("btn_change_plan").disabled = blocked;
            }

            $(window).ready(function () {
                process();
            });

        </script>
    </form:form>
</div>
</hr>
</body>
</html>