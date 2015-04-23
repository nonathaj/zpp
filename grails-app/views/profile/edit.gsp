<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="zpp"/>
		<title>Edit ${session['user'].getFullName()}'s Profile</title>
	</head>
	<body>
		<div class="well">
			<g:form controller="Profile" action="edit" role="form">
				<g:hiddenField name="id" value="${user.id}" />
				<g:hiddenField name="attempted" value="yes" />
				<div class="form-group">
					<div class="form-group">
						<label for="newUsername">Email Address</label>
						<g:field type="email" class="form-control" name="newUsername" placeholder="Enter Email Address" value="${user.username}"/>
					</div>
					<div class="form-group">
						<label for="newPassword">New Password</label>
						<g:field type="password" class="form-control" name="newPassword" placeholder="At least 7 characters long"/>
					</div>
					<div class="form-group">
						<label for="newPassword2">Verify New Password</label>
						<g:field type="password" class="form-control" name="newPassword2" placeholder="Please verify the password you entered"/>
					</div>
					<g:if test="${session['user'].isAdmin() && session['user'].id != user.id}">
						<div class="checkbox">
							<label>
								<g:checkBox name="isAdmin" checked="${user.isAdmin()}"/>
								Z++ Administrator
							</label>
						</div>
					</g:if>
					<p class="text-right"><g:actionSubmit class="btn btn-primary" action="edit" value="Update Profile"/></p>
				</div>
			</g:form>
		</div>
	</body>
</html>