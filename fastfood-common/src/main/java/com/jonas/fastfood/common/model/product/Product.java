package com.jonas.fastfood.common.model.product;

import java.io.Serializable;

/**
 * @author
 */
public class Product implements Serializable {
    private Integer id;

    private String prdName;

    private String discreb;

    private Float price;

    private Integer productNum;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrdName() {
        return prdName;
    }

    public void setPrdName(String prdName) {
        this.prdName = prdName;
    }

    public String getDiscreb() {
        return discreb;
    }

    public void setDiscreb(String discreb) {
        this.discreb = discreb;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }
}