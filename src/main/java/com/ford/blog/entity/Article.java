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
 * @ClassName: Article
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/18 下午 1:54
 **/
@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "article")
public class Article extends BaseEntity {
    @Override
    public void setEntityId(SnowflakeIdWorker snowflakeIdWorker) {
        if (StringUtils.isBlank(articleId)) {
            articleId = String.valueOf(snowflakeIdWorker.nextId());
        }
    }

    /**
     * 文章主键
     */
    @Id
    @Column(name = "id", nullable = false,length = 32, updatable = false, unique = true)
    private String articleId;
    /**
     * 文章标题
     */
    @Column(name = "title", columnDefinition = "varchar(255) comment '标题'")
    private String title;
    /**
     * 文章原文
     */
    @Column(name = "md_content", columnDefinition = "text comment 'md文件源码'")
    private String mdContent;
    /**
     * 文章HTML源码
     */
    @Column(name = "html_content", columnDefinition = "text comment 'HTML源码'")
    private String htmlContent;
    /**
     * 总结
     */
    @Column(name = "summary", columnDefinition = "text comment '总结'")
    private String summary;
    /**
     * 发表日期
     */
    @Column(name = "publish_date", columnDefinition = "datetime comment '发表日期'")
    private Date publishDate;
    /**
     * 更新日期
     */
    @Column(name = "edit_date", columnDefinition = "datetime comment '更新日期'")
    private Date editDate;
    /**
     * 阅读数
     */
    @Column(name = "page_view", columnDefinition = "int(11) comment 'pageView'")
    private Integer pageView;
    /**
     * 文章状态枚举类
     */
    public enum Status {
        /**
         * 草稿
         */
        draft("草稿"),
        /**
         * 已发表
         */
        published("已发表"),
        /**
         * 已删除
         */
        deleted("已删除");

        private final String value;

        private Status(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }
    /**
     * 文章状态
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private Status status;
}
