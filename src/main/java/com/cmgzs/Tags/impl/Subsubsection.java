package com.cmgzs.Tags.impl;

import com.cmgzs.Tags.Tag;
import lombok.Data;

/**
 * 三级标题
 */
@Data
public class Subsubsection implements Tag {
    private final String tagName = "\\subsubsection{title}content \n";
    /**
     * 标题
     */
    private String title;
    /**
     * 内容段
     */
    private String content;

    public Subsubsection(String title, String content) {
        this.title = title;
        this.content = content;
    }
    /**
     * 替换参数占位符，返回带参数的标签字符串
     *
     * @return 经过替换后的字符串
     */
    @Override
    public String getTagString() {
        return tagName.replace("title", title).replace("content", content);
    }
}
