<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="zpp"/>
		<title>Register</title>
	</head>
	<body>
		<div class="alert alert-danger">
			This page does sign a user in, however the preferred method of signing in is the form at the top of the page, please use that.
		</div>
		<div class="row">
			<div class="col-md-4 col-md-offset-4 well">
				<g:form controller="login" role="form">
					<input type="hidden" name="attempted" value="yes"/>
					<div class="form-group">
						<h2 class="form-signin-heading">Z++ Login</h2>
						<div class="form-group">
							<label for="email">Email Address</label>
							<g:field type="email" class="form-control" name="email" placeholder="Enter Email Address" required=""/>
						</div>
						<div class="form-group">
							<label for="password">Password</label>
							<g:field type="password" class="form-control" name="password" placeholder="Enter Password" required=""/>
						</div>
						<g:actionSubmit class="btn btn-primary btn-block" value="Login"/>
					</div>
				</g:form>
			</div>
		</div>
	</body>
</html>
