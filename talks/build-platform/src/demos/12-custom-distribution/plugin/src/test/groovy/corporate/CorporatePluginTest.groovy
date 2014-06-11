package corporate

import spock.lang.*
import org.gradle.api.*
import org.gradle.api.plugins.*
import org.gradle.testfixtures.ProjectBuilder

class CorporatePluginTest extends Specification {

    Project project = ProjectBuilder.builder().build()
    
    def setup() {
        project.apply plugin: CorporatePlugin
    }

    def "only allows corporate repo"() {
        expect:
        project.repositories.size() == 1
        
        when:
        project.repositories { mavenCentral() }
        
        then:
        project.repositories.size() == 1
    }
    
    def "adds integTest task"() {
        expect:
        project.tasks.findByName("integTest") == null
        
        when:
        project.apply plugin: JavaPlugin
        
        then:
        project.tasks.findByName("integTest") != null
    }
}