<%@ include file="./includes/base.jsp" %>
<!DOCTYPE html>
<html>
	<style><%@include file="/css/layout.css"%></style>
	<head>
		<meta charset="UTF-8">
		<title>${user.userId > 0 ? 'Edit Profile' : 'Register'}</title>
	</head>
	<body>
		<jsp:include page="./includes/headerLight.jsp"/>
		<main>
			<h1>${user.userId > 0 ? 'Edit Profile' : 'Register'}</h1>
			<form action="/Swap/register" method="post">
				<fieldset>
					<legend>Identity</legend>
					<div class="form-field">
						<label for="username">UserName:</label>
						<input type="text" pattern="^(?=[a-zA-Z0-9._]{2,30}$)(?!.*[_.]{2})[^_.].*[^_.]$" placeholder="ex : OldSophie68" name="username" value="${user.username}"/>
						<c:if test="${errors.containsKey('username')}" >
			   				<div class="form-error">Invalid field : ${errors.get('username')}</div>
						</c:if>
					</div>
					<div class="form-field">
						<label for="password1">Password:</label>
						<input type="password" type="text" pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,20}$" placeholder="ex : Pa$$w0rd" name="password1" value="${user.password}"/>
					</div>
					<div class="form-field">
						<label for="password2">Confirm password:</label>
						<input type="password" pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,20}$" placeholder="ex : Pa$$w0rd" name="password2" value="${user.password}"/>
						<c:if test="${errors.containsKey('password')}" >
			   				<div class="form-error">Invalid field : ${errors.get('password')}</div>
						</c:if>
					</div>
				</fieldset>
				<fieldset>
					<legend>Informations</legend>
					<div class="form-field">
						<label for="lastName">Last name:</label>
						<input type="text" pattern="^[\w'-,.][^0-9_!¡?÷?¿/\+=@#$%ˆ&*(){}|~<>;:\[\]]{2,30}$" placeholder="ex : Hatter" name="lastName" value="${user.lastName}"/>
						<c:if test="${errors.containsKey('lastName')}" >
			   				<div class="form-error">Invalid field : ${errors.get('lastName')}</div>
						</c:if>
					</div>
					<div class="form-field">
					<label for="firstName">First name:</label>
						<input type="text" pattern="^[\w'-,.][^0-9_!¡?÷?¿/\+=@#$%ˆ&*(){}|~<>;:\[\]]{2,30}$" placeholder="ex : Sophie" name="firstName" value="${user.firstName}"/>
						<c:if test="${errors.containsKey('firstName')}" >
			   				<div class="form-error">Invalid field : ${errors.get('firstName')}</div>
						</c:if>
					</div>
					<div class="form-field">
						<label for="email">Email:</label>
						<input type="email" pattern="^[\w!#$%&’*+/=?`{|}~^-]+(?:\.[\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}$" placeholder="ex : sophie.hatter@gmail.com" name="email" value="${user.email}"/>
						<c:if test="${errors.containsKey('email')}" >
			   				<div class="form-error">Invalid field : ${errors.get('email')}</div>
						</c:if>
					</div>
					<div class="form-field">
						<label for="telephone">Telephone:</label>
						<input type="tel" pattern="^[0-9]{6,15}$" placeholder="ex : 0666833445" name="telephone" value="${user.telephone}"/>
						<c:if test="${errors.containsKey('telephone')}" >
			   				<div class="form-error">Invalid field : ${errors.get('telephone')}</div>
						</c:if>
					</div>
				</fieldset>
				<jsp:include page="./includes/addressFieldset.jsp"/>
				<c:if test="${user.userId > 0}" >
		   			<p id="user-balance">Current balance: ${user.balance}</p>
				</c:if>
				<div class="btn-panel">
					<input type="submit" value="${user.userId > 0 ? 'Update' : 'Register'}" />
					<c:if test="${user.userId > 0}" >
						<jsp:include page="./includes/deleteAccount.jsp"/>
			    	</c:if>
		    	</div>
			</form>
		    <a class="btn cancel-btn" href="/Swap">Cancel</a>
		    <jsp:include page="./includes/footer.jsp"/>
		</main>
	</body>
</html>