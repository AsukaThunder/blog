package com.ford.blog.entity;

import com.ford.blog.entity.base.BaseEntity;
import com.ford.blog.util.SnowflakeIdWorker;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

/**
 * @ClassName: Tags
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/18 下午 5:57
 **/
@Getter
@Setter
@Entity
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
    /**
     * 文章标签
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tags", orphanRemoval = true)
    private List<ArticleTags> articleTags;
}
