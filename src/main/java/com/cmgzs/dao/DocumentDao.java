package com.cmgzs.dao;


import com.cmgzs.pojo.Document;

/**
 * 文档类持久化层
 */
public interface DocumentDao {

    /**
     * 保存文档示例到mongodb数据库中
     *
     * @param document 文档类
     */
    void insert(Document document);

    /**
     * 修改文档
     */
    void update();

    /**
     * 删除文档项目
     */
    void delete();
}
