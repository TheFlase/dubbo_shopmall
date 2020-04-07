package com.wgc.shopmall.inventory.entity;

import java.io.Serializable;
import java.util.Date;

public class Inventory implements Serializable {
    private Long id;

    private String itemCode;

    private String itemName;

    private Integer stockQty;

    private Integer lockedQty;

    private Integer frozenQty;

    private Date createTime;

    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public Integer getStockQty() {
        return stockQty;
    }

    public void setStockQty(Integer stockQty) {
        this.stockQty = stockQty;
    }

    public Integer getLockedQty() {
        return lockedQty;
    }

    public void setLockedQty(Integer lockedQty) {
        this.lockedQty = lockedQty;
    }

    public Integer getFrozenQty() {
        return frozenQty;
    }

    public void setFrozenQty(Integer frozenQty) {
        this.frozenQty = frozenQty;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}