package zpp

class Role {

	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}

	/***********************************************************************
	*		public static methods 
	***********************************************************************/

    public static Role getAdminRole()
    {
    	return Role.findOrSaveWhere(authority:'ROLE_ADMIN')
    }

    public static Role getUserRole()
    {
    	return Role.findOrSaveWhere(authority:'ROLE_User');
    }
}
