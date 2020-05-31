package com.my.blog.website.modal.Vo;

import java.io.Serializable;

public class CertificateVo implements Serializable {
    /**
     * post表主键
     */
    private Integer cid;

    /**
     * 内容标题
     */
    private String title;

    /**
     * 内容缩略名
     */
    private String slug;

    /**
     * 内容生成时的GMT unix时间戳
     */
    private Integer created;

    /**
     * 内容更改时的GMT unix时间戳
     */
    private Integer modified;

    /**
     * 内容所属用户id
     */
    private Integer authorId;

    /**
     * 内容类别
     */
    private String type;

    /**
     * 内容状态
     */
    private String status;

   private String valiCode;

   private String certifyingAuthority;
    /**
     * 分类列表
     */
    private String categories;

    private String imageUrl;

    /**
     * 实验号
     */
    private String laboratoryNumber;

    /**
     * 总质量
     */
    private String totalMass;

    /**
     * 实验结果
     */
    private String testResult;

    /**
     * 点击次数
     */
    private Integer hits;

    private Integer limitNum;

    private String remark;

    private String tags;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getModified() {
        return modified;
    }

    public void setModified(Integer modified) {
        this.modified = modified;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getValiCode() {
        return valiCode;
    }

    public void setValiCode(String valiCode) {
        this.valiCode = valiCode;
    }

    public String getCertifyingAuthority() {
        return certifyingAuthority;
    }

    public void setCertifyingAuthority(String certifyingAuthority) {
        this.certifyingAuthority = certifyingAuthority;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLaboratoryNumber() {
        return laboratoryNumber;
    }

    public void setLaboratoryNumber(String laboratoryNumber) {
        this.laboratoryNumber = laboratoryNumber;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public String getTotalMass() {
        return totalMass;
    }

    public void setTotalMass(String totalMass) {
        this.totalMass = totalMass;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
