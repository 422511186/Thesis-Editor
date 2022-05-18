package com.cmgzs.commons;

/**
 * 项目类型
 */
public enum DocumentTypes {

    /**
     * 主要用来排版学术论文、学术报告等
     */
    ARTICLE(0, "ARTICLE"),
    /**
     * 格式有文章结构，主要用来排版综述类、长篇论文、报告等
     */
    REPORT(1, "REPORT"),
    /**
     * 主要用来排版出版的书籍，有明显的章节结构
     */
    BOOK(2, "BOOK"),
    /**
     * 主要用来排版中文的文章，内容同article相似
     */
    CTEXART(3, "CTEXART"),
    /**
     * 主要用来排版中文的文章，内容同ctexbook 相似
     */
    CTEXBOOK(4, "CTEXBOOK"),
    /**
     * 主要用来排版中文的文章，内容同ctexrep 相似
     */
    CTEXREP(5, "CTEXREP"),
    /**
     * 学术论文模板
     */
    PROC(6, "PROC"),
    /**
     * 主要用于个人简历
     */
    MODERNCV(7, "MODERNCV");

    private int type;
    private String name;

    DocumentTypes(int type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * 根据type值获取枚举示例
     *
     * @param type
     * @return 默认为CTEXART
     */
    public static DocumentTypes DocumentType(int type) {
        for (DocumentTypes documentTypes : DocumentTypes.values()) {
            if (documentTypes.getType() == type) {
                return documentTypes;
            }
        }
        return CTEXART;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
