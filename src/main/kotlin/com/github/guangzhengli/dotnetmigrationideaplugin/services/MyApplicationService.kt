package com.github.guangzhengli.dotnetmigrationideaplugin.services

import com.github.guangzhengli.dotnetmigrationideaplugin.MyBundle

class MyApplicationService {

    init {
        println(MyBundle.message("applicationService"))

        System.getenv("CI")
            ?: TODO("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.")
    }
}
