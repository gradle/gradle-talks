package org.gradle.services.shared;

import org.gradle.shared.Person;

public class TestTest extends junit.framework.TestCase {
    private String name;

    public void method() {
        new Person("someName");
        throw new RuntimeException();
    }

}
