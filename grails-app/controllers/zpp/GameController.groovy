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
		//for now, find all the games that are upcoming: TODO give the user options on what games to display here, filtering
		def games = Game.findAllByEndGreaterThan(new Date());
    	return [games:games, message_success:params.message_success, message_failure: params.message_failure];
    }

    @Secured(['IS_AUTHENTICATED_REMEMBERED'])	//Must be logged in
    def create()
    {
    	if(!params.attempted)		//this will disable all messages for the first load of the page
    		return [];
    	if(params.name == null)
    		return [message_failure: "Please enter a valid name for the game."];
    	if(params.shortName == null || params.shortName.length() > 12)
    		return [message_failure: "Please enter a valid shortName for the game."];
		if(params.start == null)
			return [message_failure: "Please enter a value start date"];
		if(params.end == null)
			return [message_failure: "Please enter a value end date"];
		
		def start = new Date(Long.parseLong(params.start) * 1000);
		def end = new Date(Long.parseLong(params.end) * 1000);

        def new_game = new Game(
			name: params.name, 
			shortName: params.shortName, 
			start: start, 
			end: end, 
			created: new Date(), 
			rules:"Here is where your game's rules will appear",
			accepted: session["user"].isAdmin()
		);		
        new_game.save(failOnError: true);
		new_game.addPlayer(session["user"], true, PlayerType.INACTIVE);
		
		redirect(action:"list", params: [message_success:"Successfully Created Game for " + params.name]);
    }
	
	def view()
	{
		if(params.id == null)
			redirect(action:"list");
		
		def game = Game.findByShortName(params.id);
		if(game == null)
			redirect(action:"list");
		
		return [game: params.id];	
	}
}