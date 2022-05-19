package com.cmgzs.Tags.impl;

import com.cmgzs.Tags.Tag;
import lombok.Data;

/**
 * 显示   --  标签
 */
@Data
public class Maketitle implements Tag {
    private final String tagName = "\\maketitle \n";
    /**
     * 替换参数占位符，返回带参数的标签字符串
     *
     * @return 经过替换后的字符串
     */
    @Override
    public String getTagString() {
        return tagName;
    }

}
