<%@ include file="./includes/base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="./includes/basicLinks.html"/>
		<title>Manage categories</title>
	</head>
	<body>
		<jsp:include page="./includes/header.jsp"/>
		<main>
			<h1>Manage Swap categories</h1>
			<form action="/Swap/admin/categories" method="post">
				<fieldset>
					<legend>Delete a category</legend>
					<label for="category">Category to delete : </label>
		            <select name="category" required>
		                <c:forEach var="category" items="${categoriesList}">
							<option value="${category.id}">${category.label}</option>
						</c:forEach>
		            </select>
		            <label for="substitute">Substitute category for existing auctions : </label>
		            <select name="substitute" required>
		                <c:forEach var="category" items="${categoriesList}">
							<option value="${category.id}">${category.label}</option>
						</c:forEach>
		            </select>
					<input type="submit" class="btn submit2" value="Delete category" />
				</fieldset>
			</form>
			<form action="/Swap/admin/categories" method="post">
				<fieldset>
					<legend>Create a category</legend>
					<label for="category">Enter new category : </label>
					<input type="text" name="category" min="2" required>
					<input type="submit" class="btn submit1" value="Create category" />
				</fieldset>
			</form>
		</main>
		<jsp:include page="./includes/footer.jsp"/>
	</body>
</html>