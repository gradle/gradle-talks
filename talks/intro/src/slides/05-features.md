# Features

Some of them.

## Wrapper

The Gradle Wrapper is a lightweight jar and some starter scripts that you check in to source control.

When you invoke a Gradle build via the wrapper script, Gradle will automatically download the required Gradle version and use it if the user doesn't have it.

It can download from the public servers or your own repository.

Benefits:

* No need to pre-install Gradle
* Pins the build to the required Gradle version

There is a [screencast](http://gradleware.com/registered/screencasts/the-gradle-wrapper/) available that demos this.

## Custom Distributions

Gradle distributions can be customised to include enterprise specific “init scripts”.

These scripts are just like plugins and can do anything. 

For example…

* Lock down the allowed repositories
* Pre apply other plugins
* Establish new conventions
* Pre-configure existing conventions

Or anything else!

(There's a [screencast](http://gradleware.com/registered/screencasts/the-gradle-roadmap/) on this topic)

## Daemon

The Gradle Daemon is a long lived process that does the build heavy lifting. It's a warm JVM with Gradle and Groovy pre-loaded.

Avoids the startup cost of Groovy and Gradle, and allows caches to warm and better hotspot optimisations. Designed to be transparent.

Not enabled by default.

    export GRADLE_OPTS=-Dorg.gradle.daemon=true

or…

    $ gradle build --daemon

## Dependency Cache

Existing implementations have issues.

* Concurrent read/write safe (multi process)
* Source location aware
* Rich metadata (checksums, ETags, modification dates)
* Local artefact reuse (old gradle versions, maven local)
* Fetching is optimised for HTTP

**Local cache state does not affect reproducibility.**

## Ant Import

Gradle can drive Ant builds.

    <project>
      <target name="hello" depends="intro"> 
        <echo>Hello, from Ant</echo>
      </target> 
    </project>
    
    ant.importBuild 'build.xml' 
    hello.doFirst { println 'Here comes Ant' } 
    task intro << { println 'Hello, from Gradle'}

Integration is bi-directional and rich.

    $ gradle hello

## Ant Taskdefs

It's trivial to use Ant taskdefs from Gradle.

    ant.delete dir: 'someDir' 
    ant { 
      ftp(server: "ftp.comp.org", userid: 'me', ...) { 
        fileset(dir: "htdocs/manual") { 
          include name: "**/*.html" 
        } 
        // high end 
        myFileTree.addToAntBuilder(ant, 'fileset') 
      } 
      mkdir dir: 'someDir' 
    }

## IDE support

Gradle can generate project metadata for Eclipse and IDEA.

    apply plugin: "idea"
    apply plugin: "eclipse"

There is an API for deeply customising the generated metadata.

### Tooling API

The next generation of integration uses the Tooling API, i.e. embedded Gradle. This will enable real-time bi-directional communication.

SpringSource recently produced a [viewable Webinar](https://vmwareevents.webex.com/ec0605lc/eventcenter/recording/recordAction.do;jsessionid=GC4cPXlGTPqVQS1nXcy019LMhJVyyJqDZk866vt4wTnvnCZ3nnfN!2076123583?theAction=poprecord&actname=%2Feventcenter%2Fframe%2Fg.do&actappname=ec0605lc&renewticket=0&renewticket=0&apiname=lsr.php&entappname=url0107lc&needFilter=false&&isurlact=true&rID=5306592&entactname=%2FnbrRecordingURL.do&rKey=084bb0ca3eb1f546&recordID=5306592&siteurl=vmwareevents&rnd=5025534164&SP=EC&AT=pb&format=short) on their Gradle integration with STS.