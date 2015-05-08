<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="zpp"/>
		<title>Game List</title>
	</head>
	<body>
		<div class="row">
			<div class="zpp-form well">
				<g:form controller="game" role="create">
					<input type="hidden" name="attempted" value="yes"/>
					<div class="form-group">
						<h2 class="form-signin-heading">Z++ Game Creation Form</h2>
						<div class="form-group">
							<label for="name">Game Name</label>
							<i id="nameHelp" class="glyphicon glyphicon-question-sign pull-right"
							   data-toggle="popover"
							   data-trigger="hover"
							   data-placement="bottom"
							   data-title="Long-form Name"
							   data-content="Appears in page headers and in other places with lots of room. (e.g. Western College)">
							</i>
							<g:field type="text" class="form-control" name="name" placeholder="Enter Game Name" autofocus="" required=""/>
						</div>
						<div class="form-group">
							<label for="shortName">Game Short Name</label>
							<i id="shortNameHelp" class="glyphicon glyphicon-question-sign pull-right"
							   data-toggle="popover" 
							   data-trigger="hover"
							   data-placement="bottom" 
							   data-title="Short-form Name"
							   data-content="Appears in URL and email headers. (e.g. WC)">
							</i>
							<g:field type="text" class="form-control" name="shortName" placeholder="Max 12 characters" autofocus="" required=""/>
						</div>
						<div class="form-group">
							<label for="start-display">Start Time</label>
							<g:field type='text' class="form-control datetimepicker" name="start-display" required=""/>
							<g:field type="hidden" name="start"></g:field>
						</div>
						<div class="form-group">
							<label for="end-display">End Time</label>
							<g:field type='text' class="form-control datetimepicker" name="end-display" required=""/>
							<g:field type="hidden" name="end"></g:field>
						</div>
						<g:actionSubmit action="create" class="btn btn-primary btn-block" value="${session['user'].isAdmin() ? 'Create Game' : 'Request Game'}"/>
					</div>
				</g:form>
			</div>
		</div>
	</body>
</html>