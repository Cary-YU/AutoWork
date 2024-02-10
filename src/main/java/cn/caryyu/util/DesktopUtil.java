package cn.caryyu.util;


import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.net.URI;

public class DesktopUtil {
    /**
     * 打开游览器默认使用百度
     * @param url
     */
    public static void browse(String url) {

        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                if (StringUtils.isBlank(url)){
                    url = "http://www.baidu.com";
                }
                desktop.browse(new URI(url));
            } catch (Exception ignored) {
            }
        }
    }
}



















