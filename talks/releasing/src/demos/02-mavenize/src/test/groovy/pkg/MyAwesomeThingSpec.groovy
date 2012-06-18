package pkg

import spock.lang.*

class MyAwesomeThingSpec extends Specification {

    def "nothing blows up"() {
        when:
        new MyAwesomeThing()
        
        then:
        notThrown Exception
    }


}