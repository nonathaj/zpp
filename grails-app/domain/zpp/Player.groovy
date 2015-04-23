package zpp

enum PlayerType 
{
	INACTIVE, HUMAN, ZOMBIE, OZ;

	public boolean isZombie()
	{
		return this == PlayerType.ZOMBIE || this == PlayerType.OZ;
	}

	public String toHiddenString()
	{
		if(this == PlayerType.INACTIVE)
			return "Inactive";
		else if(this == PlayerType.HUMAN)
			return "Human";
		else if(this == PlayerType.ZOMBIE || this == PlayerType.OZ)
			return "Zombie";
	}

	public String toPublicString()
	{
		if(this == PlayerType.INACTIVE)
			return "Inactive";
		else if(this == PlayerType.HUMAN)
			return "Human";
		else if(this == PlayerType.ZOMBIE)
			return "Zombie";
		else if(this == PlayerType.OZ)
			return "Original Zombie";
	}
	public String toString() { return toPublicString(); }
}

class Player {

	boolean isModerator;		//is this player a moderator for this game
	PlayerType type;			//What is the status of this person's participation in the game\
	String killcode;			//if the player is a HUMAN, this code is what a zombie would enter to tag them

	static belongsTo = [user: SecUser, game: Game]

    static constraints = {
    	user nullable:false
    	game nullable:false
    }

    def getHiddenTitle()
    {
    	def title = "";
    	if(isModerator)
    		title += "Moderator"
    	return type.toHiddenString + title;
    }

    def getPublicTitle()
    {
    	def title = "";
    	if(isModerator)
    		title += "Moderator"
    	return type.toPublicString + title;
    }
}
