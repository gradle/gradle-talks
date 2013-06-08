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

        server = HttpServer.create(new InetSocketAddress(port), 0)
        server.createContext("/", new HttpHandler() {
            void handle(HttpExchange httpExchange) throws IOException {
                httpExchange.responseHeaders.add("Content-Type", "text/html;charset=utf8")
                if (httpExchange.requestURI.path == "/") {
                    byte[] bytes = "<h1>Welcome to the Gradle Summit!</h1>".getBytes("utf8")
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

    int getBoundPort() {
        server ? server.address.port : -1
    }

    void stop() {
        if (server != null) {
            server.stop(0)
            server = null
        }
    }

}
