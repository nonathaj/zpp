package zpp

import org.apache.commons.lang.builder.HashCodeBuilder

class SecUserRole implements Serializable {

	private static final long serialVersionUID = 1

	SecUser secUser
	Role role

	boolean equals(other) {
		if (!(other instanceof SecUserRole)) {
			return false
		}

		other.secUser?.id == secUser?.id &&
		other.role?.id == role?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (secUser) builder.append(secUser.id)
		if (role) builder.append(role.id)
		builder.toHashCode()
	}

	static SecUserRole get(long secUserId, long roleId) {
		SecUserRole.where {
			secUser == SecUser.load(secUserId) &&
			role == Role.load(roleId)
		}.get()
	}

	static boolean exists(long secUserId, long roleId) {
		SecUserRole.where {
			secUser == SecUser.load(secUserId) &&
			role == Role.load(roleId)
		}.count() > 0
	}

	static SecUserRole create(SecUser secUser, Role role, boolean flush = false) {
		def instance = new SecUserRole(secUser: secUser, role: role)
		instance.save(flush: flush, insert: true)
		instance
	}

	static boolean remove(SecUser u, Role r, boolean flush = false) {
		if (u == null || r == null) return false

		int rowCount = SecUserRole.where {
			secUser == SecUser.load(u.id) &&
			role == Role.load(r.id)
		}.deleteAll()

		if (flush) { SecUserRole.withSession { it.flush() } }

		rowCount > 0
	}

	static void removeAll(SecUser u, boolean flush = false) {
		if (u == null) return

		SecUserRole.where {
			secUser == SecUser.load(u.id)
		}.deleteAll()

		if (flush) { SecUserRole.withSession { it.flush() } }
	}

	static void removeAll(Role r, boolean flush = false) {
		if (r == null) return

		SecUserRole.where {
			role == Role.load(r.id)
		}.deleteAll()

		if (flush) { SecUserRole.withSession { it.flush() } }
	}

	static constraints = {
		role validator: { Role r, SecUserRole ur ->
			if (ur.secUser == null) return
			boolean existing = false
			SecUserRole.withNewSession {
				existing = SecUserRole.exists(ur.secUser.id, r.id)
			}
			if (existing) {
				return 'userRole.exists'
			}
		}
	}

	static mapping = {
		id composite: ['role', 'secUser']
		version false
	}
}
