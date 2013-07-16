import org.ratpackframework.groovy.templating.TemplateRenderer

import static org.ratpackframework.groovy.RatpackScript.ratpack

ratpack {
    handlers {
        get {
            get(TemplateRenderer).render "index.html",
                    message: "Watching resources with Gradle"
        }
        assets "public"
    }
}
