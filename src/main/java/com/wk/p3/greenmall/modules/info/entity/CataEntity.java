package com.wk.p3.greenmall.modules.info.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wk.p3.greenmall.common.utils.Reflections;
import com.wk.p3.greenmall.modules.sys.entity.User;
import com.wk.p3.greenmall.modules.sys.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Administrator on 2015/12/12.
 */
public abstract class CataEntity<T> extends CataBaseEntity<T> {

    private static final long serialVersionUID = 1L;

    protected String remarks;	// 备注
    protected User createBy;	// 创建者
    protected Date createDate;	// 创建日期
    protected User updateBy;	// 更新者
    protected Date updateDate;	// 更新日期
    protected String delFlag; 	// 删除标记（0：正常；1：删除；2：审核）

    protected T parent;	// 父级编号
    protected String parentIds; // 所有父级编号
    protected String name; 	// 名称
    protected Integer sort;		// 排序

    public CataEntity() {
        super();
        this.delFlag = DEL_FLAG_NORMAL;
    }

    public CataEntity(Integer id) {
        super(id);
    }

    @Length(min=0, max=255)
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @JsonIgnore
    public User getCreateBy() {
        return createBy;
    }

    public void setCreateBy(User createBy) {
        this.createBy = createBy;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @JsonIgnore
    public User getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(User updateBy) {
        this.updateBy = updateBy;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    //@JsonIgnore
    @Length(min=1, max=1)
    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 父对象，只能通过子类实现，父类实现mybatis无法读取
     * @return
     */
    @JsonBackReference
    @NotNull
    public abstract T getParent();

    /**
     * 父对象，只能通过子类实现，父类实现mybatis无法读取
     * @return
     */
    public abstract void setParent(T parent);

    @Length(min=1, max=2000)
    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    @Length(min=1, max=100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getParentId() {
        Integer id = null;
        if (parent != null){
            id = (Integer) Reflections.getFieldValue(parent, "id");
        }
        return id;
    }

    /**
     * 插入之前执行方法，需要手动调用
     */
    @Override
    public void preInsert(){
        // 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
//        if (!this.isNewRecord){
//            setId(IdGen.uuid());
//        }
        User user = UserUtils.getUser();
        if (StringUtils.isNotBlank(user.getId())){
            this.updateBy = user;
            this.createBy = user;
        }
        this.updateDate = new Date();
        this.createDate = this.updateDate;
    }

    /**
     * 更新之前执行方法，需要手动调用
     */
    @Override
    public void preUpdate(){
        User user = UserUtils.getUser();
        if (StringUtils.isNotBlank(user.getId())){
            this.updateBy = user;
        }
        this.updateDate = new Date();
    }
}
