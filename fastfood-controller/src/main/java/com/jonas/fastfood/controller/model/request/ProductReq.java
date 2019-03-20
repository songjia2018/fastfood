package com.jonas.fastfood.controller.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@ApiModel(value = "商品")
public class ProductReq implements Serializable {

    @ApiModelProperty(name = "productId", value = "商品ID")
    private Integer productId;
    @ApiModelProperty(name = "productName", value = "商品名称")
    private String productName;

    @ApiModelProperty(name = "discreb", value = "商品描述")
    private String discreb;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDiscreb() {
        return discreb;
    }

    public void setDiscreb(String discreb) {
        this.discreb = discreb;
    }
}
