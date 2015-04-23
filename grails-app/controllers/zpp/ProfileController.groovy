package zpp

import grails.plugin.springsecurity.annotation.Secured

class ProfileController 
{
    def springSecurityService

	def ProfileController()
	{
		ProfileController.metaClass.mixin(ControllerMixin)
	}

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])   //Must be logged in
    def index()
    {
        redirect(action:'list');
    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])   //Must be logged in
    def list()
    {
        //def numUsersToShow = params.numUsersToShow ? params.numUsersToShow : 0;
        //def page = params.page ? params.page : 0;
        def users = SecUser.findAll();//params.id, [max: numUsersToShow, offset: page]);
        System.out.println("found " + users.size() + " users");
        return [users: users];
    }

	/*
     *	Allows a user to view a profile for any user of Z++
     */
    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY']) //Anyone has access
    def view() 
    {
    	def user = SecUser.findById(params.id);
    	System.out.println("Attempted to find " + params.id + "'s profile. Results = " + user);
    	if(user != null)
			return [user: user];
		else
			redirect(controller:'home');
    }

    /*
     *	Allows a user to edit a Z++ account
     */
    @Secured(['IS_AUTHENTICATED_REMEMBERED'])	//Must be logged in
    def edit()
    {
        if(params.id == null)
            redirect(controller:"home", action:"index", params:[message_failure: "Invaid profile to edit."]);

        def newPassword = null, newEmail = null, changedAdmin = false;
        def user = SecUser.findById(params.id);

        System.out.println("Attempted editing for " + params.id);

        if(user == null)
            redirect(controller:"home", action:"index", params:[message_failure: "Invaid profile to edit."]);

    	if(params.attempted == null)		//this will disable all messages for the first load of the page
    	   return [user: user];

    	//If the user is attempting to change the password
    	if(params.newUsername != null && params.newUsername != user.username)
    	{
    		//if the user entered a different username
    		if(SecUser.findByUsername(params.newUsername) != null)
    			return [message_failure: "That email is already a registered account", user:user];
    		else
    			newEmail = params.newUsername;
    	}

    	//If the user is attempting to change the password
    	if( (params.newPassword != null || params.newPassword2 != null) && params.newPassword != "")
    	{
    		if(params.newPassword != params.newPassword2)
				return [message_failure: "Passwords do not match.", user:user];
			else
				newPassword = params.newPassword;
		}

        //if the user is attempting to update the admin status
        if(params.isAdmin != user.isAdmin())
        {
            if(params.isAdmin)
                SecUserRole.create(user, Role.getAdminRole(), true);
            else
                SecUserRole.remove(user, Role.getAdminRole(), true);
            changedAdmin = true;
        }

		if(newPassword != null)
		{
			System.out.println("Updated " + user.username + "'s password.");
			user.password = newPassword;
		}
		if(newEmail != null)
		{
			System.out.println("Updated " + user.username + "'s email to " + newEmail);
			user.username = newEmail;
		}

		if(newEmail == null && newPassword == null && !changedAdmin)
			return [message_success:"No changes were made to your profile.", user:user]

        if(newEmail != null || newPassword != null)
        {
    		user.save(failOnError: true, flush: true);

            if(user.id == session['user'].id)
                session['user'] = user;
        }

		[message_success: "Profile updated successfully.", user:user]
    }
}
