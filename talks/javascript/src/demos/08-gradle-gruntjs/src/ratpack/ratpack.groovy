import static ratpack.groovy.Groovy.*

ratpack {
    handlers {
        get {
            render groovyTemplate("index.html", message: "Watching resources with Gradle")
        }
        assets "public"
    }
}
