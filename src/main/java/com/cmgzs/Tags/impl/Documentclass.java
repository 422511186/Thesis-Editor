package com.cmgzs.Tags.impl;

import com.cmgzs.Tags.Tag;
import lombok.Data;

import java.util.ArrayList;

/**
 * 文档类标签
 */
@Data
public class Documentclass implements Tag {
//    private static final int ID = 1;
    private static String tagName = "\\documentclass[option]{class-name}";

    /**
     * 替换参数占位符，返回带参数的标签字符串
     *
     * @param option    配置
     * @param className 类型
     * @return
     */
    public static String getTagString(ArrayList<String> option, String className) {
        StringBuffer option_str = new StringBuffer();
        if (option != null && option.size() > 0) {
            option.forEach(e -> option_str.append(e).append(","));
            option_str.deleteCharAt(option_str.length() - 1);
        }
        return tagName.replace("option", option_str.toString()).replace("class-name", className);
    }
}
