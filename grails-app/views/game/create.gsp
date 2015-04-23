<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="zpp"/>
		<title>Game List</title>
	</head>
	<body>
		<div class="row">
			<div class="col-md-4 col-md-offset-4 well">
				<g:form controller="game" role="create">
					<input type="hidden" name="attempted" value="yes"/>
					<div class="form-group">
						<h2 class="form-signin-heading">Z++ Game Creation Form</h2>
						<div class="form-group">
							<label for="name">Game Name</label>
							<g:field type="text" class="form-control" name="name" placeholder="Enter Game Name" autofocus="" required=""/>
						</div>
						<div class="form-group">
							<label for="name">Game Name</label>
							<g:field type="text" class="form-control" name="startTime" placeholder="Enter Game Name" required="" class="date-time-picker"/>
						</div>
						<g:actionSubmit action="create" class="btn btn-primary btn-block" value="${session['user'].isAdmin() ? 'Create Game' : 'Request Game'}"/>
					</div>
				</g:form>
			</div>
		</div>
	</body>
</html>