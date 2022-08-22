package com.github.guangzhengli.dotnetmigrationideaplugin.services

import com.intellij.openapi.project.Project
import com.github.guangzhengli.dotnetmigrationideaplugin.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))

    }
}
