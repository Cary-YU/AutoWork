package cn.caryyu.util;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class ToolkitUtil {

    /**
     * 把文本放入剪贴板
     * @param text
     */
    public static void setClipboard(String text){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        Transferable transferable = new StringSelection(text);
        clipboard.setContents(transferable,null);
    }

}
