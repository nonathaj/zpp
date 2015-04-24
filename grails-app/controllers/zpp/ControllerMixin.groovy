package zpp

class ControllerMixin
{
	def checkForUser()
	{
		if(!session["user"])
			redirect(controller: "home")
	}
}