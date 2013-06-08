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
                "http://${findLocalAddress()}:$app.address.port"
            }
        }
    }

    private static findLocalAddress() {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces()
        while (interfaces.hasMoreElements()) {
            def current = interfaces.nextElement()
            if (!current.isUp() || current.isLoopback() || current.isVirtual()) continue
            Enumeration<InetAddress> addresses = current.getInetAddresses()
            while (addresses.hasMoreElements()) {
                InetAddress currentAddress = addresses.nextElement()
                if (currentAddress.isLoopbackAddress()) continue
                if (!(currentAddress instanceof Inet4Address)) continue
                return currentAddress.getHostAddress()
            }
        }
    }

}
