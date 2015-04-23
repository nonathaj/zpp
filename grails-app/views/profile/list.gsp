<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="zpp"/>
		<title>User List</title>
	</head>
	<body>
		<g:each in="${users}" var="user">
			<div class="well well-sm row">
				<g:link controller='Profile' action="view" params="[id:user.id]">
					${user.getFullName()}
				</g:link>
				<g:if test="${session['user']}">
					<g:if test="${session['user'].isAdmin()}">
						<div class="pull-right">
							<g:link controller='Profile' action="edit" params="[id:user.id]" class="btn btn-primary">Edit Profile</g:link>
						</div>
					</g:if>
				</g:if>
			</div>
		</g:each>
	</body>
</html>