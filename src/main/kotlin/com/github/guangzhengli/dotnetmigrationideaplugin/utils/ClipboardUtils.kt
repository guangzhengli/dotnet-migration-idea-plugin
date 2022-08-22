package com.github.guangzhengli.dotnetmigrationideaplugin.utils

import java.awt.Toolkit
import java.awt.datatransfer.Transferable
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.StringSelection
import java.lang.Exception

object ClipboardUtils {
    var clipboardString: String?
        get() {
            val clipboard = Toolkit.getDefaultToolkit().systemClipboard
            val trans = clipboard.getContents(null)
            if (trans != null) {
                if (trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    try {
                        return trans.getTransferData(DataFlavor.stringFlavor) as String
                    } catch (ignored: Exception) {
                    }
                }
            }
            return null
        }
        set(text) {
            try {
                val clipboard = Toolkit.getDefaultToolkit().systemClipboard
                val trans: Transferable = StringSelection(text)
                clipboard.setContents(trans, null)
            } catch (ignored: Exception) {
            }
        }
}