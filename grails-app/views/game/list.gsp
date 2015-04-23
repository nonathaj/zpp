<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="zpp"/>
		<title>Game List</title>
	</head>
	<body>
		<g:if test="${session['user']}">
			<g:form controller="game" action="create">
				<g:submitButton class="btn btn-primary" value="${session['user'].isAdmin() ? 'Create Game' : 'Request Game'}" name="Register"/>
			</g:form>
		</g:if>
		<g:each in="${games}" var="game">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">${game.name}</h3>
				</div>
				<div class="panel-body">
					${game.start}
				</div>
			</div>
		</g:each>
	</body>
</html>