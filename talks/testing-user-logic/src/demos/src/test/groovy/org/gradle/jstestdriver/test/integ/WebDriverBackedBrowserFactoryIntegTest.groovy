package org.gradle.jstestdriver.test.integ

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import org.gradle.jstestdriver.browser.webdriver.WebDriverBackedBrowserFactory
import org.openqa.selenium.chrome.ChromeDriver
import spock.lang.Specification

class WebDriverBackedBrowserFactoryIntegTest extends Specification {

    def "can start real browser"() {
        given:
        boolean called = false

        def server = HttpServer.create(new InetSocketAddress(0), 0)
        server.createContext("/", new HttpHandler() {
            public void handle(HttpExchange exchange) throws IOException {
                called = true
                String response = "ok";
                exchange.sendResponseHeaders(200, response.length())
                OutputStream os = exchange.getResponseBody()
                os.write(response.getBytes())
                os.close()
            }
        });

        and:
        def factory = new WebDriverBackedBrowserFactory({ new ChromeDriver() })

        when:
        server.start()
        def port = server.address.port
        def browser = factory.open("http://localhost:$port")

        then:
        called

        cleanup:
        browser?.close()
    }

}
