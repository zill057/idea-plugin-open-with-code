package com.github.zill057.ideapluginopenwithcode.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.ui.Messages
import icons.Icons


class TextSelectedAction : AnAction() {

    override fun update(e: AnActionEvent) {
        val selectedText = e.getData(PlatformDataKeys.EDITOR)?.selectionModel?.selectedText
        if (selectedText == null) {
            e.presentation.isEnabledAndVisible = false
        }
    }

    override fun actionPerformed(e: AnActionEvent) {
        val selectedText = e.getData(PlatformDataKeys.EDITOR)?.selectionModel?.selectedText
        if (selectedText == null) {
            Messages.showMessageDialog("Please select contents firstly", "", Icons.PLUGIN_ICON)
        } else {
            val escapedText = selectedText
                    .replace("\"", "\\\"")
                    .replace("$", "\\$")
                    .replace("`", "\\`")
            ProcessBuilder("bash", "-c", "echo \"$escapedText\" | code -").start()
        }
    }
}