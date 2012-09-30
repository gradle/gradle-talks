package org.gradle.api.plugins.cobertura

import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.SourceSet

class CoberturaSourceSetExtension {

    private final SourceSet sourceSet

    CoberturaSourceSetExtension(SourceSet sourceSet) {
        this.sourceSet = sourceSet
    }

    FileCollection output
    File classesDir
    FileCollection coberturaClasspath
    File serFile
    List<String> ignores

}
