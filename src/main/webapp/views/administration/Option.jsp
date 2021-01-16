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

    <title>${name}</title>
    <link rel="icon" href="<spring:url value='/images/eCareIcon.png'/>">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
        <form:form action="${current_action}" method="POST" style="min-width: 1200px;" modelAttribute="option">
            <div class="row">
                <div class="col">
                    <div class="form card p-3 bg-dark">
                        <div class="text-center mb-4">
                            <img class="mb-4" src="<spring:url value='/images/eCareIcon.png'/>" alt="" width="100"
                                 height="100">
                            <h1 class="h3 mb-3 font-weight-normal">${current_action_title}</h1>
                        </div>

                        <h3>Option</h3>
                        <hr>

                        <div class="form-group">
                            <label for="nameInput">Name</label>
                            <form:input type="text" path="value.name" class="form-control"
                                        id="nameInput" aria-describedby="nameHelp"
                                        placeholder="Enter name"></form:input>
                            <form:errors class="text-danger" path="value.name"/>
                        </div>

                        <label for="priceInput">Price</label>
                        <div class="input-group">
                            <form:input type="number" min="0.01" step="0.1" path="value.price" class="form-control"
                                        id="priceInput" placeholder="Enter last name"></form:input>
                            <form:errors class="text-danger" path="value.price"/>
                            <div class="input-group-append">
                                <span class="input-group-text">$</span>
                            </div>
                        </div>

                        <label for="turnOnPriceInput">Turn on price</label>
                        <div class="input-group">
                            <form:input type="number" min="0.0" step="0.1" path="value.turnOnPrice" class="form-control"
                                        id="turnOnPriceInput" placeholder="Enter last name"></form:input>
                            <form:errors class="text-danger" path="value.turnOnPrice"/>
                            <div class="input-group-append">
                                <span class="input-group-text">$</span>
                            </div>
                        </div>

                        <br/>
                        <div class="form-group">
                            <label for="descriptionInput">Description</label>
                            <form:textarea type="text" path="value.description" class="form-control"
                                           id="descriptionInput"
                                           placeholder="Enter description"
                                           style="min-height: 200px;"></form:textarea>
                            <form:errors class="text-danger" path="value.description"/>
                        </div>
                        <button class="btn btn-md btn-primary btn-block pull-right" type="submit"
                                style="max-width: 200px;">Save
                        </button>
                    </div>
                </div>

                    <%--OPTIONS RULES--%>
                <div class="col">
                    <div class="form card p-3 bg-dark">
                        <br>
                        <h3>Option restrictions</h3>
                        <hr>
                        <br>

                        <table class="table">
                            <thead class="thead-dark">
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Name</th>
                                <th scope="col">Rule</th>
                                <th scope="col"></th>
                            </tr>
                            <c:forEach items="${option.restrictions}" var="rule" varStatus="status">
                                <tr>
                                    <td>
                                        <form:select class="form-select form-control-sm" id="optionSelect"
                                                     path="restrictions[${status.index}].optionId2">
                                            <form:options items="${option.allOptionNames}"></form:options>
                                        </form:select>
                                        <form:errors class="text-danger" path="restrictions[${status.index}]"/>
                                    </td>
                                    <td>
                                        <form:select class="form-select form-control-sm" id="ruleSelect"
                                                     path="restrictions[${status.index}].rule">
                                            <form:options items="${rule.rule.values()}"
                                                          itemLabel="typeStr"></form:options>
                                        </form:select>
                                    </td>
                                    <td>
                                        <button class="btn btn-secondary" type="submit" name="remove_rule"
                                                value="${status.index}">Remove
                                        </button>
                                        <c:if test="${status.index==option.restrictions.size()-1}">
                                            <button class="btn btn-secondary" type="submit" name="add_rule"><i
                                                    class="fa fa-plus"></i></button>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${option.restrictions.size()==0}">
                                <tr>
                                    <td/>
                                    <td/>
                                    <td/>
                                    <td>
                                        <button class="btn btn-secondary" type="submit" name="add_rule"><i
                                                class="fa fa-plus"></i></button>
                                    </td>
                                </tr>
                            </c:if>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>

</body>
</html>