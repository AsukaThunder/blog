package com.ford.blog.entity;

import com.ford.blog.entity.base.BaseEntity;
import com.ford.blog.util.SnowflakeIdWorker;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ClassName: Tags
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/18 下午 5:57
 **/
@Entity
@Getter
@Setter
@DynamicUpdate
@Table(name = "tags")
public class Tags extends BaseEntity {
    @Override
    public void setEntityId(SnowflakeIdWorker snowflakeIdWorker) {
        if (StringUtils.isBlank(tagId)) {
            tagId = String.valueOf(snowflakeIdWorker.nextId());
        }
    }

    /**
     * 标签主键
     */
    @Id
    @Column(name = "id", nullable = false,length = 32, updatable = false, unique = true)
    private String tagId;
    /**
     * 标签名称
     */
    @Column(name = "name")
    private String name;
}
