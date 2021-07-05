<%@ include file="./includes/base.jsp" %>
<c:set var="targetUser" scope="request" value="${user}"/>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="./includes/basicLinks.html"/>
		<title>${targetUser.userId > 0 ? 'Edit Profile' : 'Register'}</title>
	</head>
	<body>
		<jsp:include page="./includes/header.jsp"/>
		<main>
			<h1>${targetUser.userId > 0 ? 'Edit Profile' : 'Register'}</h1>
			<form action="/Swap/register" method="post">
				<fieldset>
					<legend>Identity</legend>
					<div class="form-field">
						<label for="username">UserName:</label>
						<input type="text" pattern="^(?=[a-zA-Z0-9._]{2,30}$)(?!.*[_.]{2})[^_.].*[^_.]$" placeholder="ex : OldSophie68" name="username" value="${targetUser.username}"/>
						<c:if test="${errors.containsKey('username')}" >
			   				<div class="form-error">Invalid field : ${errors.get('username')}</div>
						</c:if>
					</div>
					<c:choose>
						<c:when test="${targetUser.userId > 0}">
							<a href="/Swap/account/change-password" class="btn submit1">Change password</a>
						</c:when>
						<c:otherwise>
							<div class="form-field">
								<label for="password1">Password:</label>
								<input type="password" type="text" pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,20}$" placeholder="ex : Pa$$w0rd" name="password1"/>
							</div>
							<div class="form-field">
								<label for="password2">Confirm password:</label>
								<input type="password" pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,20}$" placeholder="ex : Pa$$w0rd" name="password2"/>
								<c:if test="${errors.containsKey('password')}" >
					   				<div class="form-error">Invalid field : ${errors.get('password')}</div>
								</c:if>
							</div>
						</c:otherwise>
					</c:choose>
				</fieldset>
				<fieldset>
					<legend>Informations</legend>
					<div class="form-field">
						<label for="lastName">Last name:</label>
						<input type="text" pattern="^[\w'-,.][^0-9_!¡?÷?¿/\+=@#$%ˆ&*(){}|~<>;:\[\]]{2,30}$" placeholder="ex : Hatter" name="lastName" value="${targetUser.lastName}"/>
						<c:if test="${errors.containsKey('lastName')}" >
			   				<div class="form-error">Invalid field : ${errors.get('lastName')}</div>
						</c:if>
					</div>
					<div class="form-field">
					<label for="firstName">First name:</label>
						<input type="text" pattern="^[\w'-,.][^0-9_!¡?÷?¿/\+=@#$%ˆ&*(){}|~<>;:\[\]]{2,30}$" placeholder="ex : Sophie" name="firstName" value="${targetUser.firstName}"/>
						<c:if test="${errors.containsKey('firstName')}" >
			   				<div class="form-error">Invalid field : ${errors.get('firstName')}</div>
						</c:if>
					</div>
					<div class="form-field">
						<label for="email">Email:</label>
						<input type="email" pattern="^[\w!#$%&’*+/=?`{|}~^-]+(?:\.[\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}$" placeholder="ex : sophie.hatter@gmail.com" name="email" value="${targetUser.email}"/>
						<c:if test="${errors.containsKey('email')}" >
			   				<div class="form-error">Invalid field : ${errors.get('email')}</div>
						</c:if>
					</div>
					<div class="form-field">
						<label for="telephone">Telephone:</label>
						<input type="tel" pattern="^[0-9]{6,15}$" placeholder="ex : 0666833445" name="telephone" value="${targetUser.telephone}"/>
						<c:if test="${errors.containsKey('telephone')}" >
			   				<div class="form-error">Invalid field : ${errors.get('telephone')}</div>
						</c:if>
					</div>
				</fieldset>
				<jsp:include page="./includes/addressFieldset.jsp"/>
				<c:if test="${targetUser.userId > 0}" >
		   			<p id="user-balance">Current balance: ${targetUser.balance}</p>
				</c:if>
				<div class="btn-panel">
					<input type="submit" class="btn submit1" value="${targetUser.userId > 0 ? 'Update' : 'Register'}" />
					<a class="btn submit2" href="/Swap">Cancel</a>
		    	</div>
			</form>
			<c:if test="${targetUser.userId > 0}" >
				<jsp:include page="./includes/deleteAccountBtn.jsp"/>
			</c:if>
		</main>
		<jsp:include page="./includes/footer.jsp"/>
	</body>
</html>