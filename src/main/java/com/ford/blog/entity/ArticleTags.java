package com.ford.blog.entity;

import com.ford.blog.entity.base.BaseEntity;
import com.ford.blog.util.SnowflakeIdWorker;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @ClassName: ArticleTags
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/19 下午 4:12
 **/
@Getter
@Setter
@Entity
@Table(name = "article_tags")
@DynamicUpdate
public class ArticleTags extends BaseEntity {
    @Override
    public void setEntityId(SnowflakeIdWorker snowflakeIdWorker) {
        if (StringUtils.isBlank(articleTagId)) {
            articleTagId = String.valueOf(snowflakeIdWorker.nextId());
        }
    }

    /**
     * 文章标签关系表
     */
    @Id
    @Column(name = "article_tag_id", length = 32, updatable = false, unique = true)
    private String articleTagId;

    /**
     * 文章
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", foreignKey = @ForeignKey(name = "fk_tags_article"))
    private Article article;

    /**
     * 标签
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", foreignKey = @ForeignKey(name = "fk_article_tag"))
    private Tags tags;
}
