<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">

<jsp:include page="fragments/header.jsp"></jsp:include>

<body>

<jsp:include page="fragments/nav.jsp"/>

<div class="container">
    <div class="row py-2">
        <div class="col-12">
            <c:url value="/upsert" var="productPostUrl"/>
            <form action="${productPostUrl}" method="post">
                <input type="hidden" id="id" name="id" value="${product.id}">
                <div class="form-group">
                    <label>Name</label>
                    <input type="text" class="form-control" id="name" name="name" placeholder="Enter name"
                           value="${product.name}">
                </div>
                <div class="form-group">
                    <label>Description</label>
                    <input type="text" class="form-control" id="description" name="description" placeholder="Enter description"
                           value="${product.description}">
                </div>
                <div class="form-group">
                    <label>Price</label>
                    <input type="number" class="form-control" id="price" name="price" placeholder="Enter price"
                           value="${product.price}">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</div>

</body>
<jsp:include page="fragments/footer.jsp"/>
</html>