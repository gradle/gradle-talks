package server

class RunningApplicationFactory {

    private static final ThreadLocal<EmbeddedApplication> EMBEDDED_APP_HOLDER = new ThreadLocal<>() {
        protected Object initialValue() {
            createEmbeddedApp()
        }
    }

    private static EmbeddedApplication createEmbeddedApp() {
        def application = new EmbeddedApplication(0)
        Runtime.addShutdownHook {
            application.stop()
        }
        application
    }

    static RunningApplication get() {
        EmbeddedApplication app = EMBEDDED_APP_HOLDER.get()
        app.start()
        new RunningApplication() {
            String getBaseUrl() {
                "http://localhost:$app.boundPort"
            }
        }
    }

}
