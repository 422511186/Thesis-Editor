package com.cmgzs.pojo;

import com.cmgzs.Tags.Tag;
import lombok.Data;

@Data
public class Content {

    /**
     * 唯一标识
     */
    private String uid;

    /**
     * 标签
     */
    private Tag tag ;

    /**
     * 内容
     */
    private String body;
}
