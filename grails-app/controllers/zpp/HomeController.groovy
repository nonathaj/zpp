package zpp

import grails.plugin.springsecurity.annotation.Secured

class HomeController {

    def springSecurityService

	def HomeController()
	{
		HomeController.metaClass.mixin(ControllerMixin)
	}

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])  //Anyone has access
    def index() 
    {
    	[message_success:params.message_success, message_failure: params.message_failure]
    }

    /*
     *	Allows a user to register for an account on Z++
     */
     @Secured(['IS_AUTHENTICATED_ANONYMOUSLY']) //Anyone has access
    def register()
    {
    	if(!params.attempted)		//this will disable all messages for the first load of the page
    		return [];

    	if(params.username == null)
    		return [message_failure: "Please enter a valid email address."];

    	if(SecUser.findByUsername(params.username) != null)
    		return [message_failure: "That email is already a registered account"];

    	if(params.password == null || params.password2 == null)
			return [message_failure: "Please enter a valid password."];

		if(params.password != params.password2)
			return [message_failure: "Passwords do not match."];

		if(params.fname == null)
			return [message_failure: "Please enter a valid first name."];

		if(params.lname == null)
			return [message_failure: "Please enter a valid last name."];

        //Create the new user
    	def new_user = new SecUser(fname:params.fname, lname:params.lname, username: params.username, password: params.password);
        new_user.save(failOnError: true);

        //Apply standard user rules to the user
        SecUserRole.create(new_user, Role.getUserRole(), true)

		System.out.println("Created user " + new_user);
		login();
    }

	/*
     *	After a user is logged in (Using the Spring Security Core login controller), they are directed at this action, 
     *      which does some final setup for their session including setting up a copy of the SecUser in the session.  
     *      Because grails is dumb and only saves a GrailsUser, which can't be used to reach a regular SecUser
     */
     @Secured(['IS_AUTHENTICATED_ANONYMOUSLY']) //Anyone has access
    def login()
    {
        if(!springSecurityService.isLoggedIn())
        {
            redirect(action:'index')
            return []
        }

    	def loggedInUser = SecUser.findByUsername( springSecurityService.currentUser.username )
        def message_failure, message_success;

        if(loggedInUser != null)
        {
            System.out.println("Logged in as " + loggedInUser);
            session["user"] = loggedInUser;
            message_success = "Logged in as " + loggedInUser.getFullName();
        }
        else
        {
            message_failure = 'Error occured while logging in.'            
        }

    	redirect(action:"index", params:[message_failure: message_failure, message_success: message_success]);
    }

    /*
     *	Logs any user out
     */
     @Secured(['IS_AUTHENTICATED_REMEMBERED']) //User is logged in
    def logout()
    {
    	session.invalidate();
    	redirect(action: "index", params:[message_success:'Logout Successful']);
    }
}
