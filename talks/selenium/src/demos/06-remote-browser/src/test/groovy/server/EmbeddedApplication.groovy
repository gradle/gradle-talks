package server

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer

class EmbeddedApplication {

    private HttpServer server

    private final int port

    EmbeddedApplication(int port) {
        this.port = port
    }

    void start() {
        if (server != null) {
            stop()
        }

        System.setProperty("java.net.preferIPv4Stack", "true")
        server = HttpServer.create(new InetSocketAddress(port), 0)
        server.createContext("/", new HttpHandler() {
            void handle(HttpExchange httpExchange) throws IOException {
                httpExchange.responseHeaders.add("Content-Type", "text/html;charset=utf8")
                if (httpExchange.requestURI.path == "/") {
                    String userAgent = httpExchange.requestHeaders.getFirst("User-Agent")
                    byte[] bytes = "<h1>Welcome to the Gradle Summit!</h1><p>$userAgent</p>".getBytes("utf8")
                    httpExchange.sendResponseHeaders(200, bytes.length)
                    httpExchange.responseBody.withStream { OutputStream out -> out.write(bytes) }
                } else {
                    byte[] bytes = "<h1>Not Found</h1>".getBytes("utf8")
                    httpExchange.sendResponseHeaders(404, bytes.length)
                    httpExchange.responseBody.withStream { OutputStream out -> out.write(bytes) }
                }
            }
        })
        server.start()
    }

    InetSocketAddress getAddress() {
        server ? server.address : null
    }

    void stop() {
        if (server != null) {
            server.stop(0)
            server = null
        }
    }

}
