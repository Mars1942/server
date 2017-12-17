package com.ut.business.common;

//import com.alibaba.fastjson.annotation.JSONField;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Basic;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * 基础实体
 */
@MappedSuperclass
@DynamicInsert(true)
@DynamicUpdate(true)
public class BaseEntity {

    private Date createTime;

    private Date updateTime;

    //    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    //    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
