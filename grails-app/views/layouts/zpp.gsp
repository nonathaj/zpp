<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Z++ <g:layoutTitle /></title>

		<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
		<asset:javascript src="moment-with-locales.js"/>
		
  		<asset:javascript src="zpp_main.js"/> <%-- Inlcudes Twitter Bootstrap --%>
  		<asset:stylesheet src="zpp_main.css"/> <%-- Inlcudes Twitter Bootstrap --%>
		
  		<asset:stylesheet src="bootstrap-datetimepicker.css"/>
		<asset:javascript src="bootstrap-datetimepicker.min.js"/>
		
		<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
		<g:layoutHead/>
	</head>
	<body>
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<g:link controller="home" action="index" class="navbar-brand">Z++</g:link>
				</div>
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<%-- 
						<li class="${params.controller && params.controller.toUpperCase().equals('GAME') ? 'active' : ''}"><g:link controller="Game" action="list">Games</g:link></li>
						<li class="${params.controller && params.controller.toUpperCase().equals('PROFILE') ? 'active' : ''}"><g:link controller="Profile" action="list">Players</g:link></li>
						--%>
					</ul>

					<!-- Right side of the Nav Bar -->
					<ul class="nav navbar-nav navbar-right">
						<g:if test="${session['user'] != null}">
							<div class="btn-group">
								<g:link controller='Profile' action="view" params="[id:session['user'].id]" class="btn btn-primary navbar-btn">
									${session['user'].getFullName()}
								</g:link>
								<button type="button" class="btn btn-primary navbar-btn dropdown-toggle" data-toggle="dropdown">
									<span class="caret"></span>
									<span class="sr-only">Toggle Dropdown</span>
								</button>							
								<ul class="dropdown-menu" role="menu">
									<li>
										<g:link controller="Game" action="list">Games List</g:link>
									</li>
									<li>
										<g:link controller="Profile" action="list">User List</g:link>
									</li>
									<li class="divider"></li>
									<li>
										<g:link controller='Profile' action="edit" params="[id:session['user'].id]">Edit Profile</g:link>
									</li>
									<li class="divider"></li>
									<li>
										<g:link controller='Home' action="logout">Logout</g:link>
									</li>
								</ul>
							</div>
						</g:if>
						<g:else>
							<g:if test="${params.action && !params.action.toUpperCase().equals('REGISTER')} ">
								<li>
									<form action='/zpp/j_spring_security_check' method='POST' id='loginForm' class='navbar-form' autocomplete='off'>
										<div class="form-group">
											<input type="email" class="form-control" name="j_username" placeholder="Email">
											<input type="password" class="form-control" name="j_password" placeholder="Password">
										</div>
										<g:submitButton name="Login" class="btn btn-success" value="Login"/>
									</form>
								</li>
								<li>
									<g:form controller="home" action="register" class="navbar-form">
										<g:submitButton class="btn btn-info" value="Register" name="Register"/>
									</g:form>
								</li>
							</g:if>
						</g:else>
					</ul>
				</div>
			</div>
		</nav>
		<div class="container">
			<g:if test="${message_failure}">
				<div class="alert alert-danger alert-dismissible">${message_failure}</div>
			</g:if>
			<g:if test="${message_success}">
				<div class="alert alert-success alert-dismissible">${message_success}</div>
			</g:if>
			<g:layoutBody/>
		</div>
	</body>
</html>
