<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="zpp"/>
		<title>${user.getFullName()}'s Profile</title>
	</head>
	<body>
		<div class="page-header">
			<h2>
				${user.getFullName()}
				<g:if test="${user.isAdmin()}">
					<small>Z++ Administrator</small>
				</g:if>
				<g:if test="${session['user'] && (session['user'].isAdmin() || user.id == session['user'].id)}">
					<div class="pull-right">
						<g:link controller='Profile' action="edit" params="[id:user.id]" class="btn btn-primary">Edit</g:link>
					</div>
				</g:if>
			</h2>
		</div>
		<div class="well">
			<g:if test="${user.player != null}">
				<p>${user.player.isModerator ? "Moderator" : ""} ${user.player.type} in ${user.player.game} Game</p>
			</g:if>
		</div>
	</body>
</html>