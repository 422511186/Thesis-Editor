package com.cmgzs.utils;

import java.io.File;

/**
 * 文件操作工具类
 */
public class FileUtils {

    /**
     * 删除文件夹（包括其中的文件）
     *
     * @param dirFile
     * @return 删除结果
     */
    public static boolean deleteFile(File dirFile) {
        // 如果传进来的dir为空
        if (dirFile == null)
            return false;
        // 如果dir对应的文件不存在，则退出
        if (!dirFile.exists()) {
            return false;
        }
        if (dirFile.isFile()) {
            return dirFile.delete();
        } else {
            for (File file : dirFile.listFiles()) {
                deleteFile(file);
            }
        }
        return dirFile.delete();
    }


}
