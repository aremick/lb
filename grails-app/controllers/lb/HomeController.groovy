package lb

import grails.plugin.springsecurity.annotation.Secured

class HomeController {

    @Secured(['permitAll'])
    def index() {

    }

    @Secured(['ROLE_ADMIN'])
    def adminOnly() { render 'admin' }
}
