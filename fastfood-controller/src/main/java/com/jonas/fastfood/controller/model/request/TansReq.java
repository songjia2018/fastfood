package com.jonas.fastfood.controller.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @ClassName TansReq
 * @Description TOD
 * @Author songjia
 * @Date 2018/12/1714:07
 * @Verstion 1.0
 **/
@ApiModel(value = "事务")
public class TansReq implements Serializable {

    @ApiModelProperty(name = "userId", value = "用户ID")
    private Integer userId;
    @ApiModelProperty(name = "productId", value = "商品ID")
    private String productId;
    @ApiModelProperty(name = "productName", value = "商品名称")
    private String productName;
    @ApiModelProperty(name = "describ", value = "商品描述")
    private String describ;
    @ApiModelProperty(name = "price", value = "价格")
    private Float price;
    @ApiModelProperty(name = "productNum", value = "数量")
    private Integer productNum;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescrib() {
        return describ;
    }

    public void setDescrib(String describ) {
        this.describ = describ;
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
