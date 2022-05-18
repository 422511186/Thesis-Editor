package com.cmgzs.service;

import java.io.File;

public interface LaTexService {

    /**
     * 创建一个临时存储文件夹
     *
     * @param fileName uuid
     * @return
     */
    boolean Mkdir(String fileName);


    /**
     * 写入文件信息(格式Tex)
     *
     * @param fileName 文件的绝对路径
     * @param Tex      文件内容
     * @return 操作是否成功
     */
    File Save(String fileName, String Tex);

    /**
     * 编译Tex
     *
     * @param fileName 文件名字
     * @return 编译完成的PDF文件名称
     */
    String Compile(String fileName);


}
