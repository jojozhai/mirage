/*
 * 项目名称：mirage-goods
 * 类名称: AbstractGoods
 * 创建时间: 2016年9月5日 下午2:57:30
 * 创建人: zhailiang@pz365.com
 *
 * 修改历史:
 * 
 * Copyright: 2016 www.pz365.com Inc. All rights reserved.
 * 
 */
package com.ymt.mirage.goods.domain;

import java.math.BigDecimal;

import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

import com.ymt.pz365.data.jpa.domain.Goods;
import com.ymt.pz365.data.jpa.domain.SortableImpl;

/**
 *
 *
 * @author zhailiang@pz365.com
 * @version 1.0.0
 */
@MappedSuperclass
public class AbstractGoods extends SortableImpl implements Goods {
    
    /**
     * 名称
     */
    private String name;
    /**
     * 分享标题
     */
    private String shareTitle;
    /**
     * 描述
     */
    private String shareTip;
    /**
     * 关键商品
     */
    private boolean key;
    /**
     * 图片
     */
    private String image;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 价格描述
     */
    private String priceDesc;
    /**
     * 简介
     */
    private String intro;
    /**
     * 发布
     */
    private boolean enable;
    /**
     * 描述
     */
    @Lob
    private String desc;
    /**
     * 卖出人次
     */
    private int saleCount;
    /**
     * 卖出人次
     */
    private int saleCountPlus;
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }
    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }
    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }
    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }
    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
    /**
     * @return the intro
     */
    public String getIntro() {
        return intro;
    }
    /**
     * @param intro the intro to set
     */
    public void setIntro(String intro) {
        this.intro = intro;
    }
    /**
     * @return the saleCount
     */
    public int getSaleCount() {
        return saleCount;
    }
    /**
     * @param saleCount the saleCount to set
     */
    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }
    /**
     * @return the saleCountPlus
     */
    public int getSaleCountPlus() {
        return saleCountPlus;
    }
    /**
     * @param saleCountPlus the saleCountPlus to set
     */
    public void setSaleCountPlus(int saleCountPlus) {
        this.saleCountPlus = saleCountPlus;
    }
    /**
     * @return the enable
     */
    public boolean isEnable() {
        return enable;
    }
    /**
     * @param enable the enable to set
     */
    public void setEnable(boolean enable) {
        this.enable = enable;
    }
    /**
     * @return the key
     */
    public boolean isKey() {
        return key;
    }
    /**
     * @param key the key to set
     */
    public void setKey(boolean key) {
        this.key = key;
    }
    /**
     * @return the priceDesc
     */
    public String getPriceDesc() {
        return priceDesc;
    }
    /**
     * @param priceDesc the priceDesc to set
     */
    public void setPriceDesc(String priceDesc) {
        this.priceDesc = priceDesc;
    }
    /**
     * @return the shareTitle
     */
    public String getShareTitle() {
        return shareTitle;
    }
    /**
     * @param shareTitle the shareTitle to set
     */
    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }
    /**
     * @return the shareTip
     */
    public String getShareTip() {
        return shareTip;
    }
    /**
     * @param shareTip the shareTip to set
     */
    public void setShareTip(String shareTip) {
        this.shareTip = shareTip;
    }

}
