package com.wk.p3.greenmall.modules.user.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.wk.p3.greenmall.common.persistence.BaseEntity;
import com.wk.p3.greenmall.common.utils.IdGen;
import com.wk.p3.greenmall.modules.sys.entity.User;
import com.wk.p3.greenmall.modules.sys.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wk.p3.greenmall.common.persistence.DataEntity;

/**
 * Created by cc on 15-12-14.
 */
public class Organization extends BaseEntity<Organization> {

	private static final long serialVersionUID = 1L;
	@NotBlank(message="公司名称不能为空")
	@NotNull(message="公司名称不能为空")
	@Pattern(regexp = "^([\\u4e00-\\u9fa5]{1,20}|[a-zA-Z\\.\\s]{2,30})$",message="请填写正确的公司名称")
	private String name;			// 公司名称
	private String type;			// 公司类型 (数据字典)
	private String imagePath;		// image_path
	private Date registDate;		// 注册时间
	private String isEntrust;		// 是否是委托人（没有注册）的公司信息(默认0:否，1：是)
	private String registNumber;	// 注册号
//	@NotBlank(message="请选择省份")
	private String provinceId;		// 省份
//	@NotBlank(message="请选择城市")
	private String cityId;			// 城市
//	@NotBlank(message="请选择区/县")
	private String area;			// 区/县
	private String detailArea;		// 详细地区
	private String postcode;		// 邮编
	private String faxNumber;		// 传真
	private String personName;		// 默认联系人
	private String phoneMob;		// 默认手机号
	private String phoneTel;		// 默认座机号
	private String qqNumber;		// 默认qq
	private String email;			// 默认邮箱
	private String mainType;		// 主营产品 
    private User updateBy;
    private User createBy;
    private Date updateDate;
    private Date createDate;
    private String remarks;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public User getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(User updateBy) {
        this.updateBy = updateBy;
    }

    public User getCreateBy() {
        return createBy;
    }

    public void setCreateBy(User createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getMainType() {
        return mainType;
	}

	public void setMainType(String mainType) {
		this.mainType = mainType;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	@Length(min=0, max=10, message="是否是委托人（没有注册）的公司信息(默认0:否，1：是)长度必须介于 0 和 10 之间")
	public String getIsEntrust() {
		return isEntrust;
	}

	public void setIsEntrust(String isEntrust) {
		this.isEntrust = isEntrust;
	}

	@Length(min=0, max=32, message="注册号长度必须介于 0 和 32 之间")
	public String getRegistNumber() {
		return registNumber;
	}

	public void setRegistNumber(String registNumber) {
		this.registNumber = registNumber;
	}

//	@Length(min=0, max=32, message="省份长度必须介于 0 和 32 之间")
	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

//	@Length(min=0, max=32, message="城市长度必须介于 0 和 32 之间")
	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

//	@Length(min=0, max=32, message="详细地区长度必须介于 0 和 32 之间")
	public String getDetailArea() {
		return detailArea;
	}

	public void setDetailArea(String detailArea) {
		this.detailArea = detailArea;
	}

	@Length(min=0, max=32, message="邮编长度必须介于 0 和 32 之间")
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Length(min=0, max=32, message="传真长度必须介于 0 和 32 之间")
	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPhoneMob() {
		return phoneMob;
	}

	public void setPhoneMob(String phoneMob) {
		this.phoneMob = phoneMob;
	}

	public String getPhoneTel() {
		return phoneTel;
	}

	public void setPhoneTel(String phoneTel) {
		this.phoneTel = phoneTel;
	}

	public String getQqNumber() {
		return qqNumber;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    /**
     * 插入之前执行方法，需要手动调用
     */
    @Override
    public void preInsert() {
        // 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
        if (!this.isNewRecord) {
            setId(IdGen.uuid());
        }
        User user = UserUtils.getUser();
        if (StringUtils.isNotBlank(user.getId())) {
            this.updateBy = user;
            this.createBy = user;
        }
        this.updateDate = new Date();
        this.createDate = this.updateDate;
    }

    /**
     * 插入之前执行方法，需要手动调用(并返回ID)
     */
    public String preInsertAndReturnId() {
        // 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
        String id = IdGen.uuid();
        if (!this.isNewRecord) {
            setId(id);
        }
        User user = UserUtils.getUser();
        if (StringUtils.isNotBlank(user.getId())) {
            this.updateBy = user;
            this.createBy = user;
        }
        this.updateDate = new Date();
        this.createDate = this.updateDate;
        return id;
    }

    /**
     * 更新之前执行方法，需要手动调用
     */
    @Override
    public void preUpdate() {
        User user = UserUtils.getUser();
        if (StringUtils.isNotBlank(user.getId())) {
            this.updateBy = user;
        }
        this.updateDate = new Date();
    }
}
