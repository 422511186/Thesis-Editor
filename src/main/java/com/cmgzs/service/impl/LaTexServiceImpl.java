package com.cmgzs.service.impl;

import com.cmgzs.Tags.impl.Documentclass;
import com.cmgzs.Tags.impl.Usepackage;
import com.cmgzs.commons.DocumentOption;
import com.cmgzs.pojo.Document;
import com.cmgzs.service.LaTexService;
import com.cmgzs.utils.TermUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class LaTexServiceImpl implements LaTexService {
    //    临时文件存放父目录
    @Value(value = "${File.path}")
    private String path;

    /**
     * 创建一个临时存储文件夹
     *
     * @param fileName uuid
     * @return
     */
    @Override
    public boolean Mkdir(String fileName) {
        boolean mkdirs = false;
        while (!mkdirs) {
            mkdirs = new File(path, fileName).mkdirs();
        }
        return mkdirs;
    }

    /**
     * 写入文件信息(格式Tex)
     *
     * @param fileName 文件名字 uuid
     * @param Tex      文件内容
     * @return 操作是否成功
     */
    @Override
    public File Save(String fileName, String Tex) {
        File file = new File(path + "/" + fileName, fileName + ".tex");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.append(Tex);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 编译Tex
     *
     * @param fileName 文件名字
     * @return 编译完成的PDF文件名称
     */
    @Override
    public String Compile(String fileName) {
        //  执行编译
        TermUtils.exeCmd("src/main/resources/scripts/latexmk.bat PDFS " + path + fileName + "/" + fileName);
        return fileName + ".pdf";
    }

    /**
     * 将Document文档对象整合为代码段，保存编译。
     *
     * @param document 文档对象
     * @return 代码段
     */
    @Override
    public String convert(Document document) throws IllegalAccessException {
        StringBuilder res = new StringBuilder();
        //  解析排版配置参数
        ArrayList<String> optionList = DocumentOption.getOptionList(document.getOptions());
        // 获取文档类型
        String class_name = document.getClass_name().getName();
        // 文档头
        res.append(new Documentclass(optionList, class_name).getTagString());
        // 解析包结构
        for (Usepackage p : document.getPackages()) {
            res.append(p.getTagString());
        }
        // 添加额外的配置
        res.append("\\newcommand{\\tabincell}[2]{\\begin{tabular}{@{}#1@{}}#2\\end{tabular}}\n");

        return res.toString();
    }
}
