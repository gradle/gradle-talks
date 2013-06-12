package tests

import geb.Page

class FrontPage extends Page {

    static url = "/"

    static content = {
        heading { $("h1").text() }
    }
}
