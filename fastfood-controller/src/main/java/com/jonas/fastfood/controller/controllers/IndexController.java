package com.jonas.fastfood.controller.controllers;


import com.jonas.fastfood.common.constants.Const;
import com.jonas.fastfood.common.mvc.BackendSessionUtil;
import com.jonas.fastfood.common.utils.SecurityCodeUtil;
import com.jonas.fastfood.common.utils.U;
import com.jonas.fastfood.common.utils.json.JsonResult;
import com.jonas.fastfood.controller.model.request.REQVerifCode;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(value = "IndexController", tags = "引导页")
@RestController
@RequestMapping("/index")
public class IndexController {

    @ApiOperation(value = "获取验证码", notes = "无返回参数，直接回写图片流字节")
    @ApiImplicitParams({@ApiImplicitParam(name = "width", value = "图片宽度", dataType = "string", paramType = "query"), @ApiImplicitParam(name = "height", value = "图片高度", dataType = "string", paramType = "query"), @ApiImplicitParam(name = "count", value = "验证码个数", dataType = "string", paramType = "query"), @ApiImplicitParam(name = "style", value = "验证码样式", dataType = "string", paramType = "query")})
    @GetMapping(value = {"/code"})
    public void code(HttpServletResponse response, String width, String height, String count, String style) throws IOException {
        SecurityCodeUtil.Code code = SecurityCodeUtil.generateCode(count, style, width, height);

        // 往 session 里面丢值
        BackendSessionUtil.putImageCode(code.getContent());

        // 向页面渲染图像
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        javax.imageio.ImageIO.write(code.getImage(), "jpeg", response.getOutputStream());
    }
}