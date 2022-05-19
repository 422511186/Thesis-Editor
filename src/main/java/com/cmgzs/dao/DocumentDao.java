package com.cmgzs.dao;


import com.cmgzs.pojo.Document;

/**
 * 文档类持久化层
 */
public interface DocumentDao {
    /**
     * 保存到mongodb数据库中
     *
     * @param document 文档类
     */
    void save(Document document);
}
