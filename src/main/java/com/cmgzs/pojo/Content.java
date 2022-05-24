package com.cmgzs.pojo;

import com.cmgzs.Tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 正文
 */
@Data
public class Content implements Comparable<Content> {

    /**
     * 唯一标识（绑定格式）
     */
    private String uid;
    /**
     * 上一层id
     */
    private int pid;
    /**
     * 当前
     */
    private int id;
    /**
     * 标签
     */
    private Tag tag;
    /**
     * 内容
     */
    private String body;

    /**
     * 比较器，用于排序列表
     * 从小到大
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Content o) {
        return pid - o.getPid() == 0 ? id - o.getId() : pid - o.getPid();
    }

}
