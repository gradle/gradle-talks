package pkg

class TheTest extends GroovyTestCase {

  void testMethod() {
    assert 1 == 2
  }
  
  void testIndirect() {
    someMethod()
  }
  
  void someMethod() {
    assert 1 == 2
  }

  void testNoisy() {
    System.out.println "I'm on stdout"
    System.err.println "I'm on stderr"
  }
}