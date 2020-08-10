<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">

<jsp:include page="fragments/header.jsp"/>

<body>

<jsp:include page="fragments/nav.jsp"/>

<div class="container">
    <div class="row py-2">
        <div class="col-12">
            <c:url value="/categories/upsert" var="categoryPostUrl"/>
            <form action="${categoryPostUrl}" method="post">
                <input type="hidden" id="id" name="id" value="${category.id}">
                <div class="form-group">
                    <label>Title</label>
                    <input type="text" class="form-control" id="title" name="title" placeholder="Enter title"
                           value="${category.title}">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</div>

</body>
<jsp:include page="fragments/footer.jsp"/>
</html>