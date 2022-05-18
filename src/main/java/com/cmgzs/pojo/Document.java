package com.cmgzs.pojo;

import com.cmgzs.commons.DocumentTypes;
import lombok.Data;

import java.util.ArrayList;

/**
 * 文档项目(mongodb)
 */
@Data
public class Document {
    /**
     * 所有者
     */
    private String auth;
    /**
     * 文档类型
     */
    private DocumentTypes class_name;
    /**
     * 文档排版配置参数
     */
    private DocumentOption options;
    /**
     * 使用的宏包集合
     */
    private ArrayList<String> packages;
    /**
     * 正文（标签+文字）
     */
    private ArrayList<Content> content;
    /**
     * 正文参数配置
     */
    private ArrayList<ContentStyle> contentStyles;

}
