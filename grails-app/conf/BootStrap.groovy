import lb.Role
import lb.User
import lb.UserRole

class BootStrap {

    def init = { servletContext ->
        // define some test roles and users
        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(flush: true, failOnError: true)
        println('created admin role')
        def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(flush: true, failOnError: true)
        println('created user role')

        def user1 = User.findByUsername('Bobby') ?: new User(username: 'Bobby', enabled: true, password: 'pass').save(failOnError: true)
        if (!user1.authorities.contains(userRole)) {
            UserRole.create(user1, userRole, true)
        }

        def user2 = User.findByUsername('jenny') ?: new User(username: 'jenny', enabled: true, password: 'pass').save(failOnError: true)
        if (!user2.authorities.contains(userRole)) {
            UserRole.create(user2, userRole, true)
        }
        if (!user2.authorities.contains(adminRole)) {
            UserRole.create(user2, adminRole, true)
        }

        def user3 = User.findByUsername('aaron') ?: new User(username: 'aaron', enabled: true, password: 'pass').save(failOnError: true)
        if (!user3.authorities.contains(userRole)) {
            UserRole.create(user3, userRole, true)
        }
        if (!user3.authorities.contains(adminRole)) {
            UserRole.create(user3, adminRole, true)
        }

        // make sure that the database is well set up
        assert User.count == 3
        assert Role.count == 2
        assert UserRole.count == 5
    }

    def destroy = {
    }

}
