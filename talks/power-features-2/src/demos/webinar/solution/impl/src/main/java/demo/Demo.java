package demo;

import demoable.Demoable;
import org.apache.commons.lang.StringUtils;

public class Demo implements Demoable {

    boolean normalizeSpace = true;

    public static void main(String... args) {
        System.out.println(new Demo().demo());
    }

    public String demo() {
        String message = "This is a    nice  webinar!";
        if (normalizeSpace) {
            return StringUtils.normalizeSpace(message);
        } else {
            return message;
        }
    }
}