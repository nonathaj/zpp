package zpp

class Game 
{
    boolean accepted;       //has this game been accepted by a Z++ administrator?
	String name;
    String rules;
    Date dateCreated;
    Date start;
    Date end;
    boolean hasEnded;       //has the game ended

	static hasMany = [players: Player]

    static constraints = {
        name blank:false
        rules blank:false
        dateCreated blank:false;
        start blank:false;
        end blank:false;
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
}