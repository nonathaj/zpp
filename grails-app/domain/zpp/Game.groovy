package zpp

class Game 
{
    boolean accepted;       //has this game been accepted by a Z++ administrator?
	String name;
	String shortName;
    String rules;
    Date created;
    Date start;
    Date end;

	static hasMany = [players: Player]

    static constraints = {
		shortName blank:false, unique: true
        name blank:false
        rules blank:false
        created blank:false
        start blank:false
        end blank:false
		accepted blank:false
    }

    def getMods()
    {
    	return Player.findAllByGameAndIsModerator(this, true);
    }

    def getHumans()
    {
    	return Player.findAllByGameAndType(this, PlayerType.HUMAN);
    }

    def getZombies()
    {
    	return Player.findAllByGameAndTypeInList(this, [PlayerType.ZOMBIE, PlayerType.OZ] );
    }

    def getOZs()
    {
        return Player.findAllByGameAndType(this, PlayerType.OZ);
    }

    def isActive()
    {
        Date now = new Date();
        return start.before(now) && end.after(now);
    }

    def isOver()
    {
        return end.before(new Date());
    }
	
	public boolean addPlayer(SecUser user, boolean moderator, PlayerType type)
	{
		if(user.player != null)
			return false;
		
		def player = new Player(
			user: user,
			game: this,
			isModerator: moderator,
			type: type,
			killcode: Player.GenerateKillCode()
		);		
		addToPlayers(player);
		player.save(failOnError: true, flush: true);
		this.save(failOnError: true, flush: true);
	}
}