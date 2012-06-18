package pkg;

import java.io.PrintWriter;

public class HelloWorld {
    public void say(PrintWriter writer) {
        writer.println("Hello world!");
        writer.flush();
    }
}