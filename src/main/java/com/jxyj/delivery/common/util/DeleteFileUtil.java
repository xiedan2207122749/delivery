package com.jxyj.delivery.common.util;

import com.jxyj.delivery.sys.util.SysConstant;

import java.io.File;

/**
 * @author xieDan
 * @Classname DeletedFileUtil
 * @Description 删除文件工具类
 * @Date 2022/2/18 13:59
 * @Created by mr_xie
 */
public class DeleteFileUtil {
    
    public static void deleteFile(String filePosition) {
        filePosition = filePosition.substring(SysConstant.SERVER_IP.length(), filePosition.indexOf("?originalFileName="))
            .replace(SysConstant.REPLACE_UP_LOAD, SysConstant.FILE_PREFIX);
        File file = new File(filePosition);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }
}
