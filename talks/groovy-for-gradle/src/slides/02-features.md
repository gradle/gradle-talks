# Which

are Groovy's essential features?

## Groovy Console

* A GUI REPL (read-eval-print-loop) program
* Great for experimenting with Groovy
* Great for isolating Groovy from Gradle issues

Once Groovy is installed (e.g. via Homebrew), can be run via running `groovyConsole`.

Online version: [Groovy Web Console](http://groovyconsole.appspot.com)

# Demo

Groovy Console

## Java Compatible

Groovy supports ~95% of Java syntax.

    public class Thing {
        private String name;

        public Thing(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public String setName(String name) {
            this.name = name;
        }
    }

## Concise & expressive

Groovy removes noise by using sensible defaults.

    class Thing {
        String name

        Thing(String name) {
            this.name = name
        }
    }

* `public` by default
* Semicolons are optional
* Auto generated getter/setter
* Property syntax
* Optional `return` statement

# Groovy Feature Tour

## Strings

String literals can be single or double quote, single or multi line.

    def s1 = 'single-quoted string'
    def s2 = "double-quoted string"
    def s3 = """
        multi
        line
        string
    """

There is no character literal. 

    def c = 'G' as char

## Groovy Strings (GStrings)

Groovy supports string interpolation.

    def i = 2
    println "i is $i" // i is 2

    def name = "gradle"
    println "Training: ${name.toUpperCase()}" // Training: GRADLE

Single quoted String literals are always plain Strings.

    def name = "gradle"
    println 'Training: ${name.toUpperCase()}' // Training: ${name.toUpperCase()}

## Lists

Groovy provides syntactic sugar for dealing with lists.

    def numbers = [1, 2, 3]
    println numbers // [1, 2, 3]
    println numbers.getClass() // java.util.LinkedList
    println numbers.size() // 3
    println numbers[0] // 1
    println numbers[1] // 2

    numbers << 4
    println numbers // [1, 2, 3, 4]
    numbers[0] = 10
    println numbers // [10, 2, 3, 4]

## Maps

Groovy provides syntactic sugar for dealing with maps.

    def stats = [age: 20, name: "John Smith"]
    println stats // [age: 20, name: "John Smith"]
    println stats.getClass() // java.util.LinkedHashMap
    println stats.size() // 2

    println stats.age // 20
    println stats["age"] // 20

    stats.gender = "Male"
    println stats // [age: 20, name: "John Smith", gender: "Male"]
    stats["gender"] = "?"
    println stats // [age: 20, name: "John Smith", gender: "?"]
    
## size() method

Groovy unifies `.length`, `.length()` and `.size()`.

All collections and things that have a size are given a `.size()` method.

    assert [1,2,3].size() == 3
    assert "abc".size() == 3
    assert [a: 1, b: 2, c: 3].size() == 3
    
## Operators

Groovy supports operator overloading.

    def list = []
    list << 0
    list << 1
    list << 2
    assert list.size() == 3
    
## Groovy Truth

Every value can be coerced to a boolean.

    assert "hi there"
    assert [1, 2, 3]
    assert new Object()

    assert ""
    assert []
    assert null

    if (System.getProperty("user.name")) { ... }

## Properties

Groovy introduces the concept of properties.

    class Thing {
        String name
    }
    
    Thing thing = new Thing()
    thing.name = "Fred"  // thing.setName("Fred")
    println thing.name   // println thing.getName()

## Properties (2)

Objects are free to redefine meaning of *get property* and *set property*.

    Map m = new HashMap()
    m.name = "Fred"           // m.put("name", "Fred")
    assert m.name == "Fred"   // assert m.get("name") == "Fred"
    
## Named Parameters

Groovy has basic support for “named parameters”

    say(name: "John", age: 12)
        
    def say(Map attributes) {
        println "Name: $attributes.name"
        println "Age: $attributes.age"
    }

The named parameters map is always the first parameter in the list.

    def say(Map attributes, String name) {
        println "Name: $name"
        println "Age: $attributes.age"
        println "Gender: $attributes.gender"
    }

    say("John", age: 12, gender: "Male")
    
## Scripts

Besides classes, Groovy also has scripts.

Scripts can contain script variables, inline code, methods, and classes.

Script variables are stored in the script's *binding* (a map-like data structure).

    name = "Fred"
    println name

A script is compiled into a subclass of `groovy.lang.Script`.

Gradle build scripts *are* Groovy scripts.

## Types

Groovy is _optionally_ typed. Variables, parameters and return values can be typed or untyped.

    int doubleTyped(int i) {
        i * 2
    }

    def doubleUntyped(i) {
        i * 2
    }

## Types (2)

Typed variable:

    int i = 1
    
Untyped variable:

    def i = 1

The `def` keyword is synonymous with `Object`.

## Type Safety

Groovy is *strongly* typed, but not *statically* typed.

Type compatibility is enforced at _runtime_.

    int doubleTyped(int i) {
        i * 2
    }
    
    def doubleUntyped(i) {
        i * 2
    }

    doubleTyped("abc") // RuntimeException
    
    doubleUntyped("abc") // what happens here?

## Optional Parentheses

When calling a method, the `()` are optional (except when they are not).

    def someMethod(arg1, arg2) { … }

    someMethod(1, 2)
    someMethod 1, 2
    
    println("Hello, Gradle Summit!")
    println "Hello, Gradle Summit!"
        
## Dynamism

Groovy is a _dynamic_ language.

Dispatch of method calls and property reads/writes is highly dynamic.

This allows behavior to be added or changed at runtime:

    class Echoer {
        def echo(int i) {
            println i
        }
    }

    Echoer.metaClass.ekko = { int i -> println i }
    def echoer = new Echoer()

    echoer.ekko(1)

## Dynamic methods

Objects can intercept calls to methods that don't physically exist.

    class Shouter {
        def say(String message) {
            println message.toLowerCase()
        }
        def methodMissing(String name, args) {
            println name.toUpperCase()
        }
    }

    def shouter = new Shouter()
    shouter.say("GradleSummit") // gradlesummit
    shouter.gradleSummit()      // GRADLESUMMIT

## Dynamic Method Names

Decide at runtime which method to call.

    class Hello {
        def helloJohn() {
            println "Hello John!"
        }
    }

    def name = "John"
    new Hello()."hello$name"()
        
## Builders

Generate data structures dynamically.

Groovy ships with builders for XML, JSON, Swing, etc.

Example: [`MarkupBuilder`](http://groovy.codehaus.org/Creating+XML+using+Groovy%27s+MarkupBuilder)

    def builder = new groovy.xml.MarkupBuilder()
    def output = builder.html {
        head { 
            title 'Building HTML the Groovy Way' 
        }
        body {
            p {
                3.times { n ->
                    a href: "doc${n}.html", "Document No. $n"
                }
            }
        }
    }

Knows nothing about HTML in particular.

## Parsers

Groovy makes parsing XML (and JSON) a breeze.

    <orders>
        <order>
            <item quantity="3">Egg</item
            <item quantity="1">Honey</item>
        </order>
    </orders>
    
<!-- -->

    def orders = new XmlSlurper().parseText(xml)
    orders.order.item.each { println it.@quantity }

## Closures

Closures are like function pointers, lambdas, anonymous classes etc.

They are declared with `{}`

    def say = { msg ->
        println msg
    }

And called like normal methods:

    say("hi") // hi

## Closure parameters

Closure parameters can be typed:

    def say = { String msg ->
        println msg
    }

Implicit `it` parameter:

    def say = {
        println it
    }
    
    say("hi")

Closures can take multiple parameters:

    def say = { a, b ->
        println a
        println b
    }
    
    say("hi", "there")

## Closure return values

Closures always return a value

    def double = {
        it * 2
    }

    assert double(2) == 4

## Closures as callbacks

Closures are often used for callbacks:

    class Action {
        Closure onStart

        void start() {
            onStart()
            println "Started"
        }
    }

    def action = new Action()
    action.onStart = { println "Starting now!" }
    action.start()

## Closures as method parameters

There's a special syntax for passing a closure as the last parameter.

    def oneArgMethod(closure) {
        closure() * 2
    }

    def twoArgMethod(factor, closure) {
        closure() * factor
    }

    oneArgMethod({ 3 }) // 6
    oneArgMethod { 3 } // 6

    twoArgMethod(3, { 3 }) // 9
    twoArgMethod(3) { 3 } // 9
    twoArgMethod 3, { 3 } // 9

## Common closure use

One common use of closures, is for iteration…

    [1, 2, 3].each {
        println it
    }

Will call the closure once for each item, passing the item to the closure as the argument.

## Closures take references

Closures keep a reference to any variables that they use.

    def name = "John"
    def sayName = {
        println name
    }
    name = "Fred"
    sayName() // "Fred"
    
## Closure Delegates

You can influence how a closure resolves symbols by changing its *delegate*.

    class Person {
        String name

        def executeInside(Closure c) {
            c.delegate = this
            c()
        }
    }

    def person = new Person()
    person.name = "John"
    def name = "Fred"
    person.executeInside { println name }
    
In Gradle, delegate always wins over lexical scope.

## Gradle & Closures

Gradle uses closures & DSLs extensively.

    repositories {
        mavenCentral()
    }

    dependencies {
        compile "commons-lang:commons-lang:2.1"
    }
    
    task jar(type: Jar) {
        from sourceSets.main.output
    }

## The GDK

The [Groovy JDK](http://groovy.codehaus.org/groovy-jdk/) enriches the JDK class library with many new *methods*.

Collections:

    [1, [2,3]].flatten() // [1, 2, 3] 
    ['a', 'b'].each { item -> println item } 
    ['a', 'b'].collect { it + '1' } // ['a1', 'b1'] 
    ['a', 'b', 'c'].findAll { it != 'c' } // ['a', 'b'] 
    [1, 2, 3].every { it < 3 } // false 
    [1, 2, 3].any { it < 3 } // true

## The GDK (2)

Files:

    def f = new File("names.txt")
    f.text = "Fred\nBarney\nWilma"
    println f.readLines().sort()
    
Strings:

    "ls -al".execute().text
    "fred".capitalize // Fred
    
Threads:

    Thread.start("worker") {
        // do work
    }
    
*Lots* of other useful GDK methods.
    
## Groovy Pitfalls
    
Groovy adds some methods to *every* object:

    if (System.hasProperty("user.name")) { ... } // never true
    
Sometimes you do *not* want GStrings:

    rename "(.*)_OEM_BLUE(.*)", '$1$2'
    
Misspelling method names:

    mispelt(5)
    
<!-- -->

    groovy.lang.MissingMethodException: No signature of method: ConsoleScript1.mispelt() is applicable for argument types: (java.lang.Integer) values: [5]
    
## Groovy Pitfalls (2)

Cannot omit both parens and comma:

    compile "com.google.guava:guava:14.0.1" {
        transitive = false
    }

<!-- -->

    > Could not find method com.google.guava:guava:14.0.1() for arguments [build_qb1cma7cb6m2s7kisg2cgekt1$_run_closure1_closure2@5c2d22f2] on root project 'java'.

## More Groovy

Groovy has many uses outside Gradle.

Particularly good for testing Java code bases.

[DZone Cheat Sheet](http://refcardz.dzone.com/refcardz/groovy) is a handy reference.

[Groovy In Action (2nd Ed)](http://www.manning.com/koenig2/) is the definitive handbook.

<img src="img/gina.jpg"/>

## Q&A

* Thanks for attending!
* Questions?
* Feedback?
* http://gradle.org
* http://gradleware.com

<img src="img/Groovy-logo.svg" />

