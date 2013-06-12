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

## Scripts

Besides classes, Groovy also supports scripts.

Scripts can contain script variables, inline code, methods, and classes.

Script variables are stored in the script's *binding* (a map-like data structure).

    name = "foo"
    println name

Inline code and methods are compiled into subclass of `groovy.lang.Script`.

## Properties

Groovy introduces the concept of properties.

    thing.name = "bar"
    println thing.name

By default, these map to getters/setters of JavaBean compliant “properties”.

However, this is a more powerful concept.

    Map m = new HashMap()

    m.put("foo", "bar")
    assert m.get("foo") == bar

    m.foo = "baz"
    assert m.foo == "baz"

Receiver can decide what *get property* and *set property* means.

## Types

Groovy is _optionally_ typed. Variables, parameters and return values can be typed or untyped.

Typed method:

    int doubleIt(int i) {
        i * 2
    }

Untyped method:

    def doubleIt(i) {
        i * 2
    }
    
Untyped method also works for other values (e.g. Strings).

## Types (2)

Typed variable:

    int i = 1
    
Untyped variable:

    def i = 1

The `def` keyword is synonymous with `Object`.

## Type Safety

Groovy is *strongly* typed, but not *statically* typed.

Type compatibility is enforced at _runtime_.

    int doubleIt(int i) {
        i * 2
    }

    doubleIt("abc") // RuntimeException

## Optional Parentheses

When calling a method, the `()` are optional (except when they are not).

    def someMethod(arg1, arg2) { … }

    someMethod(1, 2)
    someMethod 1, 2

## Strings

There is no character literal. Strings can be single or double quote, single or multi line.

    def s1 = 'a string'
    def s2 = "a string"
    def s3 = """A
        string
        on
        multiple
        lines
    """

## Groovy Strings (GStrings)

Groovy supports string interpolation.

    def i = 2
    println "i is $i" // i is 2

    def name = "gradle"
    println "Training: ${name.toUpperCase()}" // Training: Gradle
 
Single quoted String literals are always plain Strings.

    def name = "gradle"
    println 'Training: ${name.toUpperCase()}' // Training: ${name.toUpperCase()}

## GString Evaluation

GStrings are eagerly evaluated:

    def i = 2
    def s = "i is $i"
    i = 3
    assert s == "i is 2"

GStrings can be lazy with special syntax:

    def i = 2
    def s = "i is ${->i}"
    i = 3
    assert s == "i is 3"

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

Behaviour may not even really exist:

    class Shouter {
        def methodMissing(String name, args) {
            println name.toUpperCase()
        }
    }

    def shouter = new Shouter()
    shouter.iWillBeShouted()

# Demo

Dynamic Methods

## Builders

Generate data structures dynamically.

Groovy ships with builders for XML, JSON, Swing, etc.

Example: `MarkupBuilder`

    def builder = new MarkupBuilder()
    builder.html {
        head { title 'Simple document' }
        body(id: 'main') {
            h1 'Building HTML the Groovy Way'
            p {
                ul {
                    li "second item"
                }
            }
            a href: 'more.html', 'Read more...'
        }
    }

`MarkupBuilder` knows nothing about HTML in particular.

## Dynamic Method Names

Even method names can be dynamic.

    class Hello {
        def helloJohn() {
            println "Hello John!"
        }
    }

    def name = "John"
    new Hello()."hello$name"()

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

## Closures

Closures are like function pointers, lambdas, anonymous classes etc.

They are declared with `{}`

    def sayIt = { it ->
        println it
    }

And called like normal methods:

    sayIt("foo") // foo

## Closure parameters

Their parameters can be typed:

    def sayIt = { String it ->
        println it
    }

A closure with no declared parameters implicitly takes one untyped parameter named `it`

    def sayIt = {
        println it
    }

Closures can take *N* arguments

    def sayIt = { a, b ->
        println a
        println b
    }

## Closure return values

Closures always return a value

    def doubleIt = {
        it * 2
    }

    doubleIt(2) == 4

## Closures as callbacks

Closures are often used for callbacks:

    class Action {
        Closure onStart

        void start() {
            onStart()
        }
    }

    def action = new Action()
    action.onStart = { println "Starting now!" }
    action.start()

## Closures as parameters

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

## Multi line closures

Closures are often multiple lines…

    def oneArgMethod(closure) {
        closure() * 2
    }

    def twoArgMethod(factor, closure) {
        closure() * factor
    }

    oneArgMethod {
        3
    }

    twoArgMethod(3) {
        3
    }

## Common closure use

One common use of closures, is for iteration…

    [1, 2, 3].each {
        println it
    }

Will call the closure once for each item, passing the item to the closure as the argument.

## Closure symbol resolution

Closure bodies can access anything that you would normally be able to access at that point (i.e. lexical scope + instance scope).

    class Thing {
        String name = "fred"
        Closure nameSayer = {
            println name
        }
    }

    def thing = new Thing()
    thing.nameSayer() // "fred"

## Closures take references

Closures keep a reference to any variables that they use.

    def name = "John"
    def sayName = {
        println name
    }
    name = "Fred"
    sayName() // "Fred"

## The Delegate

**Very important concept!**

You can influence how a closure resolves symbols, by changing its *delegate*.

    class Person {
        String name

        def executeInside(Closure c) {
            c.delegate = this
            c()
        }
    }

    def person = new Person(name: "John")
    person.executeInside { println name }

## Closure resolution strategy

The order of “resolution” can be controlled:

    class Person {
        String name

        def executeInside(Closure c) {
            c.resolveStrategy = Closure.DELEGATE_FIRST
            c.delegate = this
            c()
        }
    }

    name = "Fred"
    def person = new Person(name: "John")
    person.executeInside { println name }

## Closure scope access

You can explicitly access the different scopes:

    class Person {
        String name

        def executeInside(Closure c) {
            c.resolveStrategy = Closure.DELEGATE_FIRST
            c.delegate = this
            c()
        }
    }

    name = "Fred"
    def person = new Person(name: "John")
    person.executeInside {
        println "owner: $owner.name (${owner.getClass()})"
        println "delegate: $delegate.name (${delegate.getClass()})"
    }

## Closures & DSLs

Many DSLs (domain specific languages) are implemented with closures + dynamic resolution:

    class Recorder {
        def record = []
        def methodMissing(String name, args) {
            record << [name: name, args: args]
        }
    }

    def recorder = new Recorder()
    def calls = {
        foo()
        bar("baz")
        somethingElse(1,2,3)
    }
    calls.delegate = recorder
    calls()

    println recorder

## Gradle & Closures

* Gradle uses closures & DSLs extensively
* Gradle always uses a resolution strategy of `DELEGATE_FIRST`

<!-- -->

    repositories {
        mavenCentral()
    }

    dependencies {
        compile "commons-lang:commons-lang:2.1"
    }

## Named Parameters

Groovy has a kind of support for “named parameters”

    def sayIt(Map attributes) {
        println "It's name is: $attributes.name"
        println "It's age is: $attributes.age"
    }

    sayIt(name: "John", age: 12)

The named parameters map is always the first parameter in the list.

    def sayIt(Map attributes, String name) {
        println "It's name is: $name"
        println "It's age is: $attributes.age"
        println "It's gender is $attributes.gender"
    }

    sayIt("John", age: 12, gender: "Male")

## The GDK

Groovy adds a lot of new behaviour to the core JDK types to make them more groovy:

    [1, [2,3]].flatten() // [1, 2, 3] 
    ['a', 'b'].each { item -> println item } 
    ['a', 'b'].collect { it + '1' } // ['a1', 'b1'] 
    ['a', 'b', 'c'].findAll { it != 'c' } // ['a', 'b'] 
    [1, 2, 3].every { it < 3 } // false 
    [1, 2, 3].any { it < 3 } // true

Not just collections, but they are the most “popular”.

## Operators

Groovy supports operator overloading and adds some new ones.

    def l = []
    l << 0
    l << 1
    l << 2
    assert l.size() == 3

## .size()

Groovy unifies `.length`, `.length()` and `.size()`.

All collections and things that have a size are given a `.size()` method.

    assert [1,2,3].size() == 3
    assert "abc".size() == 3
    assert [a: 1, b: 2, c: 3].size() == 3

