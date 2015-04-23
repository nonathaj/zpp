<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="zpp"/>
		<title>Register</title>
	</head>
	<body>
		<div class="row">
			<div class="col-md-4 col-md-offset-4 well">
				<g:form controller="home" role="form">
					<input type="hidden" name="attempted" value="yes"/>
					<div class="form-group">
						<h2 class="form-signin-heading">Z++ Account Registration</h2>
						<div class="form-group">
							<label for="fname">First Name</label>
							<g:field type="text" class="form-control" name="fname" placeholder="Enter First Name" autofocus="" required=""/>
						</div>
						<div class="form-group">
							<label for="lname">Last Name</label>
							<g:field type="text" class="form-control" name="lname" placeholder="Enter Last Name" required=""/>
						</div>
						<div class="form-group">
							<label for="username">Email Address</label>
							<g:field type="email" class="form-control" name="username" placeholder="Your email will act as your username for logging in" required=""/>
						</div>
						<div class="form-group">
							<label for="password">Password</label>
							<g:field type="password" class="form-control" name="password" placeholder="Please enter a password" required=""/>
						</div>
						<div class="form-group">
							<label for="password2">Verify Password</label>
							<g:field type="password" class="form-control" name="password2" placeholder="Please verify the password you entered" required=""/>
						</div>
						<g:actionSubmit action="register" class="btn btn-primary btn-block" value="Register"/>
					</div>
				</g:form>
			</div>
		</div>
	</body>
</html>
