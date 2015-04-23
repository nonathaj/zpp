package zpp

import grails.plugin.springsecurity.annotation.Secured

class GameController 
{
    def springSecurityService

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY']) //Anyone has access
    def index() 
    {
        redirect(action:"list");
    }

    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY']) //Anyone has access
    def list() 
    {
    	def games = Game.findAllByHasEnded(false);
    	return [games:games];
    }

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])	//Must be logged in
    def create()
    {
    	if(!params.attempted)		//this will disable all messages for the first load of the page
    		return [];
    	if(params.name == null)
    		return [message_failure: "Please enter a valid name for the game."];

        def new_game = new Game(hasEnded: false);
        new_game.save(failOnError: true);
    }
}