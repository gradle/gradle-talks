package pkg;

import java.io.PrintWriter;

public class Main {
    
    public static void main(String[] args) {
        new HelloWorld().say(new PrintWriter(System.out));
    }
    
}