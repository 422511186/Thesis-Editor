package com.cmgzs.dao.impl;

import com.cmgzs.dao.DocumentDao;
import com.cmgzs.pojo.Document;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentDaoImpl implements DocumentDao {

    /**
     * 保存文档示例到mongodb数据库中
     *
     * @param document 文档类
     */
    @Override
    public void insert(Document document) {

    }

    /**
     * 修改文档
     */
    @Override
    public void update() {

    }

    /**
     * 删除文档项目
     */
    @Override
    public void delete() {

    }
}
