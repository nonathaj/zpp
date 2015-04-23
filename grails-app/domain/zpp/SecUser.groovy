package zpp

class SecUser {

	/***********************************************************************
	*		Z++ items 
	***********************************************************************/

	String fname, lname;
    Player player;                          //the current player object associated with the game the user is participating in

    public String toString()
    {
    	return username;
    }

    def getFullName()
    {
        return fname + " " + lname
    }

    public boolean isAdmin()
    {
    	return this.authorities.any { it.authority == "ROLE_ADMIN" }
    }

	/***********************************************************************
	*		All items associated with 
	***********************************************************************/

	transient springSecurityService

	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static transients = ['springSecurityService']

	static constraints = {
		username email: true, blank: false, unique: true
		password blank: false

		//Z++ constraints
		fname blank: false
    	lname blank: false
        player nullable:true
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		SecUserRole.findAllBySecUser(this).collect { it.role }
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}
}
