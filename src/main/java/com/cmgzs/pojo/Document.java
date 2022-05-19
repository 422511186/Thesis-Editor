package com.cmgzs.pojo;

import com.cmgzs.Tags.impl.Usepackage;
import com.cmgzs.commons.DocumentOption;
import com.cmgzs.commons.DocumentTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * 文档项目(mongodb存储结构)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document {
    /**
     * 所有者
     */
    private String auth;
    /**
     * 实例唯一标识
     */
    private String uid;
    /**
     * 文档名称
     */
    private String Name;
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
    private ArrayList<Usepackage> packages;
    /**
     * 正文（标签 + 文字）
     */
    private ArrayList<Content> content;
    /**
     * 正文参数配置
     */
    private ArrayList<ContentStyle> contentStyles;
}
