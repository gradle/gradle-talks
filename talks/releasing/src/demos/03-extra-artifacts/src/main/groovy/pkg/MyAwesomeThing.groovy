package pkg

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

class MyAwesomeThing {

    private Log log = LogFactory.getLog(MyAwesomeThing)

    MyAwesomeThing() {
        log.info("created!!")
    }
}