package com.cmgzs.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 文件操作工具类
 */
@Component
@Slf4j
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

    /**
     * 异步删除文件(防止阻塞)
     *
     * @param file
     */
    @Async("taskExecutor")
    public void delFiles(File file) {
        log.info("异步删除文件(防止阻塞)");
        //  自旋删除临时文件
        boolean delete = deleteFile(file.getParentFile());
        if (!delete) {
            try {
                Thread.sleep(300);
                deleteFile(file);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException("文件夹删除异常");
            }
        }
    }

}
