package com.jonas.fastfood.controller.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "验证码")
public class REQVerifCode {
    @ApiModelProperty(name = "width", value = "图片宽度")
    private String width;
    @ApiModelProperty(name = "height", value = "图片高度")
    private String height;
    @ApiModelProperty(name = "count", value = "验证码个数")
    private String count;
    @ApiModelProperty(name = "style", value = "样式")
    private String style;

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
