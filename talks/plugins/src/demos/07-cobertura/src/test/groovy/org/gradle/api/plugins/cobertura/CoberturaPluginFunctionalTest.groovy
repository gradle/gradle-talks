package org.gradle.api.plugins.cobertura

import org.gradle.api.plugins.cobertura.util.FunctionalSpec

class CoberturaPluginFunctionalTest extends FunctionalSpec {

    def setup() {
        buildFile << applyPlugin(CoberturaPlugin)
    }

    def "tasks operation"() {
        given:
        buildFile << """
            apply plugin: "java"

            repositories {
                mavenCentral()
            }

            dependencies {
                testCompile "junit:junit:4.10"
            }
        """
        and:
        file("src/main/java/p/MyClass.java") << """
        package p;

        public class MyClass {

            public void someMethod() {
                int a = 1 + 1;
            }

        }
        """

        file("src/test/java/p/MyClassTest.java") << """
        package p;

        public class MyClassTest {

            @org.junit.Test
            public void someMethod() {
                new MyClass().someMethod();
            }

        }
        """

        when:
        run "check"

        then:
        wasExecuted ":coberturaInstrumentMain"
        wasExecuted ":test"
        wasExecuted ":testCoberturaReport"

        when:
        run "check", "-i"

        then:
        wasUpToDate ":coberturaInstrumentMain"
        wasUpToDate ":test"
        wasUpToDate ":testCoberturaReport"
    }

    def "does not conflict with Gradle - due to use of asm"() {
        given:
        buildFile << """
            apply plugin: "groovy"

            repositories {
                mavenCentral()
            }

            dependencies {
                compile gradleApi()
                groovy localGroovy()
            }
        """

        file("src/test/groovy/GradleCodeTest.groovy") << """
            import org.gradle.api.Project;
            import org.gradle.testfixtures.ProjectBuilder;
            import org.junit.Test;

            class GradleCodeTest {

                @Test
                void applyPlugin() {
                    def project = ProjectBuilder.builder().withName("testProject").build()
                    project.apply(plugin: "java")
                }
            }
        """

        when:
        run "check"

        then:
        wasExecuted ":coberturaInstrumentMain"
    }

    def "handles interfaces properly"() {
        given:
        buildFile << """
            apply plugin: "groovy"

            repositories {
                mavenCentral()
            }

            dependencies {
                testCompile "junit:junit:4.10"
                groovy localGroovy()
            }
        """

        and:
        file("src/main/groovy/p/MyInterface.groovy") << """
        package p
        interface MyInterface {
            Boolean passesTest()
        }
        """

        and:
        file("src/main/groovy/p/MyClass.groovy") << """
        package p
        class MyClass implements MyInterface {
            Boolean passesTest() {
               return (Math.random() <= 1)
            }
        }
        """

        file("src/test/groovy/p/MyClassTest.groovy") << """
            package p
            import org.junit.Test

            class MyClassTest {

                @Test
                void passesTest() {
                    def myClass = new MyClass()
                    assert myClass.passesTest() == true
                }
            }
        """

        when:
        run "test"

        then:
        notThrown ClassNotFoundException
    }

    def "handles interfaces properly"() {
        given:
        buildFile << """
            apply plugin: "groovy"

            repositories {
                mavenCentral()
            }

            dependencies {
                testCompile "junit:junit:4.10"
                groovy localGroovy()
            }
        """

        and:
        file("src/main/groovy/p/MyInterface.groovy") << """
        package p
        interface MyInterface {
            Boolean passesTest()
        }
        """

        and:
        file("src/main/groovy/p/MyClass.groovy") << """
        package p
        class MyClass implements MyInterface {
            Boolean passesTest() {
               return (Math.random() <= 1)
            }
        }
        """

        file("src/test/groovy/p/MyClassTest.groovy") << """
            package p
            import org.junit.Test

            class MyClassTest {

                @Test
                void passesTest() {
                    def myClass = new MyClass()
                    assert myClass.passesTest() == true
                }
            }
        """

        when:
        run "test"

        then:
        notThrown ClassNotFoundException
    }
}
