<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta path="viewport" content="width=device-width, initial-scale=1">

    <title>${Plan.name}</title>
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
    <div class="d-flex justify-content-center">
        <form:form action="${current_action}" method="POST" modelAttribute="Plan">
        <div class="form card p-3 bg-dark" style="min-width: 700px;">
            <div class="text-center mb-4">
                <img class="mb-4" src="<spring:url value='/images/eCareIcon.png'/>" alt="" width="100"
                     height="100">
                <h1 class="h3 mb-3 font-weight-normal">${current_action_title}</h1>
            </div>
            <h2>${Plan.name}</h2>
            <hr/>
            <br/>

            <div class="row">
                <div class="col">
                    <div class="form-group">
                        <label for="nameInput">Name</label>
                        <form:input type="text" path="name" class="form-control"
                                    id="nameInput" aria-describedby="nameHelp" placeholder="Enter name"></form:input>
                        <form:errors class="text-danger" path="name"/>
                    </div>
                </div>
                <div class="col">
                    <label for="priceInput">Price</label>
                    <div class="input-group">
                        <form:input type="number" min="0.1" step="0.1" path="price" class="form-control"
                                    id="priceInput" placeholder="Enter last name"></form:input>
                        <form:errors class="text-danger" path="price"/>
                        <div class="input-group-append">
                            <span class="input-group-text">$</span>
                        </div>
                    </div>
                </div>
            </div>

            <h4>Available options</h4>
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Description</th>
                    <th scope="col">Price</th>
                    <th scope="col">Turn on price</th>
                    <th scope="col">Included in plan</th>
                    <th scope="col">Undisablable</th>
                </tr>
                <c:forEach items="${Plan.options}" var="option" varStatus="status">
                    <tr>
                        <td>
                                ${option.name}
                            <p>
                            <form:errors class="text-danger" path="options[${status.index}]"/>
                            </p>
                        </td>
                        <td>${option.description}</td>
                        <td>${option.price}$</td>
                        <td>${option.turnOnPrice}$</td>
                        <td>
                            <form:checkbox id="option_included_${status.index}" path="options[${status.index}].enabled"
                                           class="form-check-input" onchange="onIncluded(this);"></form:checkbox>
                        </td>
                        <td>
                            <form:checkbox id="option_undisablable_${status.index}" path="options[${status.index}].undisablable"
                                           class="form-check-input"></form:checkbox>
                        </td>
                    </tr>
                </c:forEach>
                </thead>
            </table>
            <form:errors class="text-danger" path="options"/>
            <button class="btn btn-md btn-primary" type="submit" style="max-width: 200px;">Save</button>
            </form:form>
            <script>
                $(document).ready(function() {
                    let elements = document.querySelectorAll("[id*=option_included_]");
                    for (var i = 0, length = elements.length; i < length; i++) {
                        child = elements[i];
                        onIncluded(child);
                    }
                });

                function onIncluded(e) {
                    let template = "option_included_"; // FIXME: not so good
                    let checkbox = e;
                    let index = checkbox.id.substring(template.length);
                    if (checkbox.checked)
                    {
                        document.getElementById('option_undisablable_'+index).disabled = false;
                    } else {
                        document.getElementById('option_undisablable_'+index).disabled = true;
                    }
                }
            </script>
        </div>
    </div>
</div>

</body>
</html>