package com.github.guangzhengli.dotnetmigrationideaplugin.utils

import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.Notifications
import com.intellij.notification.NotificationListener.UrlOpeningListener
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project
import java.lang.Exception

object NotifyUtils {
    private val NOTIFICATION = NotificationGroup("migration", NotificationDisplayType.BALLOON, false)
    private const val COMMAND_COPIED = "dotnet6 migration command copied to clipboard"
    fun notifyMessageDefault(project: Project?) {
        notifyMessage(project, COMMAND_COPIED)
    }

    fun notifyMessage(project: Project?, message: String?) {
        try {
            val currentNotify = NOTIFICATION.createNotification(message!!, NotificationType.INFORMATION)
            Notifications.Bus.notify(currentNotify, project)
        } catch (ignored: Exception) {
        }
    }

    fun notifyMessage(project: Project?, message: String?, type: NotificationType) {
        try {
            val currentNotify = NOTIFICATION.createNotification("", message!!, type, UrlOpeningListener(true))
            Notifications.Bus.notify(currentNotify, project)
        } catch (ignored: Exception) {
        }
    }
}