package com.github.guangzhengli.dotnetmigrationideaplugin.actions

import com.github.guangzhengli.dotnetmigrationideaplugin.utils.ClipboardUtils
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
import java.util.regex.Pattern

class CpURLAction : AnAction() {
    companion object {
        private const val ROUTE_NAME_REGEX = "name:(.*?),"
        private const val ROUTE_TEMPLATE_REGEX = "routeTemplate:(.*?),"
        private const val ROUTE_HTTP_METHOD_REGEX = "HttpMethod\\.(.*?)\\)"
        private const val ROUTE_CONTROLLER_REGEX = "controller=\"(.*?)\""
        private const val ROUTE_METHOD_REGEX = "action=\"(.*?)\""
    }

    override fun actionPerformed(e: AnActionEvent) {
        val editor = e.getData(PlatformDataKeys.EDITOR)!!
        val selectedText = editor.selectionModel.selectedText?.replace("\\s+".toRegex(), "") ?: return
        val name = findByRegex(selectedText, ROUTE_NAME_REGEX)
        val template = findByRegex(selectedText, ROUTE_TEMPLATE_REGEX) ?: return
        val httpMethod = findByRegex(selectedText, ROUTE_HTTP_METHOD_REGEX) ?: return

        val route = "[Http$httpMethod($template, Name = $name)]"
        ClipboardUtils.clipboardString = route

        val controller = findByRegex(selectedText, ROUTE_CONTROLLER_REGEX) ?: return
        val method = findByRegex(selectedText, ROUTE_METHOD_REGEX) ?: return
        val project = CommonDataKeys.PROJECT.getData(e.dataContext) ?: return


    }

    private fun findByRegex(selectedText: String, regex: String): String? {
        val pattern = Pattern.compile(regex, Pattern.MULTILINE)
        val matcher = pattern.matcher(selectedText)
        return if (matcher.find()) {
            matcher.group(1)
        } else null
    }


    private fun getControllerFile(project: Project, controller: String, method: String): Array<out PsiFile> {
        val controllerName = controller + "Controller"
        return FilenameIndex.getFilesByName(project, controllerName, GlobalSearchScope.allScope(project))
    }
}