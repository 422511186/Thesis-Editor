package com.cmgzs.Tags;

/**
 * Tex标签接口
 */
public interface Tag {
    /**
     * 替换参数占位符，返回带参数的标签字符串
     *
     * @return 经过替换后的字符串
     */
    public String getTagString();
}
