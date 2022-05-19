package com.cmgzs.dao.impl;

import com.cmgzs.dao.DocumentDao;
import com.cmgzs.pojo.Document;
import org.springframework.stereotype.Service;

@Service
public class DocumentDaoImpl implements DocumentDao {

    /**
     * 保存到mongodb数据库中
     *
     * @param document 文档类
     */
    @Override
    public void save(Document document) {

    }
}
