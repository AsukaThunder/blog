package com.ford.blog.entity;

import com.ford.blog.entity.base.BaseEntity;
import com.ford.blog.util.SnowflakeIdWorker;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName: Comments
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/19 上午 10:38
 **/
@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "comments")
public class Comments extends BaseEntity {
    @Override
    public void setEntityId(SnowflakeIdWorker snowflakeIdWorker) {
        if (StringUtils.isBlank(commentId)) {
            commentId = String.valueOf(snowflakeIdWorker.nextId());
        }
    }

    /**
     * 评论主键
     */
    @Id
    @Column(name = "id", nullable = false,length = 32, updatable = false, unique = true)
    private String commentId;
    /**
     * 父主键
     */
    @Column(name = "parent_id", columnDefinition = "varchar(32) comment '父主键'")
    private String parentId;
    /**
     * 评论内容
     */
    @Column(name = "content")
    private String content;
    /**
     * 评论时间
     */
    @Column(name = "comment_date")
    private Date commentDate;
    /**
     * 评论与用户关系
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk1_comment_user"))
    private User user;
    /**
     * 评论与文章关系
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", foreignKey = @ForeignKey(name = "fk1_comment_article"))
    private Article article;

}
