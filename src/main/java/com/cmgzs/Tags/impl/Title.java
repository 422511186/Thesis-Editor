package com.cmgzs.Tags.impl;

import com.cmgzs.Tags.Tag;
import lombok.Data;

/**
 * 题目   --  标签
 */
@Data
public class Title implements Tag {

    private final String tagName = "\\title{content}   \n";
    private String content;

    public Title(String content) {
        this.content = content;
    }

    /**
     * 替换参数占位符，返回带参数的标签字符串
     *
     * @return 经过替换后的字符串
     */
    @Override
    public String getTagString() {
        return tagName.replace("content", content);
    }
}
