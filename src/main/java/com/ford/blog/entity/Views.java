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
 * @ClassName: Views
 * @author: Ford.Zhang
 * @version: 1.0
 * 2019/12/19 上午 10:48
 **/
@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "views")
public class Views extends BaseEntity {
    @Override
    public void setEntityId(SnowflakeIdWorker snowflakeIdWorker) {
        if (StringUtils.isBlank(viewId)) {
            viewId = String.valueOf(snowflakeIdWorker.nextId());
        }
    }

    /**
     * 访问量主键
     */
    @Id
    @Column(name = "id", nullable = false,length = 32, updatable = false, unique = true)
    private String viewId;
    /**
     * 计算日期
     */
    @Column(name = "count_date")
    private Date countDate;
    /**
     * 访问量
     */
    @Column(name = "views")
    private String views;
    /**
     * 访问与用户关系
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk1_user_views"))
    private User user;


}
