<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="zpp"/>
		<title>Game List</title>
	</head>
	<body>
		<g:if test="${session['user']}">
			<g:form controller="game" action="create">
				<g:submitButton style="margin-bottom:15px" class="btn btn-primary" value="${session['user'].isAdmin() ? 'Create Game' : 'Request Game'}" name="Register"/>
			</g:form>
		</g:if>
		<g:each in="${games}" var="game">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">${game.name} <small>${game.players.size()} Players</small></h3>
				</div>
				<div class="panel-body">
					<p>Starts at <span class="format-unix-date">${game.start.getTime()/1000}</span></p>
					<p>Ends at <span class="format-unix-date">${game.end.getTime()/1000}</span></p>
				</div>
			</div>
		</g:each>
	</body>
</html>