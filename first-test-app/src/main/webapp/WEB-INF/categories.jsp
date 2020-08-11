<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">

<jsp:include page="fragments/header.jsp"/>

<body>

<jsp:include page="fragments/nav.jsp"/>

<div class="container">
    <div class="row py-2">
        <div class="col-12">
            <c:url value="/categories/new" var="newCategoryUrl"/>
            <a class="btn btn-primary" href="${newCategoryUrl}">Add category</a>
        </div>

        <div class="col-12">
            <table class="table table-bordered my-2">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Title</th>
                </tr>
                </thead>
                <tbody>

                <%--                <% for (Product prod : repository.findAll()) { %>--%>
                <c:forEach var="cat" items="${requestScope.categories}">
                    <tr>
                        <th scope="row">
                            <c:out value="${cat.id}"/>
                                <%--                        <%= prod.getId() %>--%>
                        </th>
                        <td>
                            <c:out value="${cat.title}"/>
                                <%--                        <%= prod.getName() %>--%>
                        </td>
                        <td>
                            <c:url value="/categories/edit" var="categoryEditUrl">
                                <c:param name="id" value="${cat.id}"/>
                            </c:url>
                            <a class="btn btn-success" href="${categoryEditUrl}"><i class="fas fa-edit"></i></a>
                            <c:url value="/categories/delete" var="categoryDeleteUrl">
                                <c:param name="id" value="${cat.id}"/>
                            </c:url>
                            <a class="btn btn-danger" href="${categoryDeleteUrl}"><i class="far fa-trash-alt"></i></a>
                        </td>
                    </tr>
                </c:forEach>
                <%--                <% } %>--%>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
<jsp:include page="fragments/footer.jsp"/>
</html>
