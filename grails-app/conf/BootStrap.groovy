
import zpp.SecUser
import zpp.Role
import zpp.SecUserRole

class BootStrap {

    def init = { servletContext ->

    	def adminRole = Role.getAdminRole()
    	def admin = SecUser.findOrSaveWhere(username:'admin@admin.com', password:"admin", fname:"admin_fname", lname:"admin_lname")

    	if(!admin.authorities.contains(adminRole))
    		SecUserRole.create(admin, adminRole, true)

    	def userRole = Role.getUserRole()
    	def user = SecUser.findOrSaveWhere(username:'user@user.com', password:"user", fname:"user_fname", lname:"user_lname")

    	if(!user.authorities.contains(userRole))
    		SecUserRole.create(user, userRole, true)
    }
    def destroy = {
    }
}
