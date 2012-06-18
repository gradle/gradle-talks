package pkg;

import junit.framework.TestCase;
import pkg.HelloWorld;

import java.io.PrintWriter;
import java.io.StringWriter;

public class HelloWorldTest extends TestCase {
    public void testWrite() {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        new HelloWorld().say(pw);

        assertEquals("Hello world!\n", sw.toString());
    }

}
