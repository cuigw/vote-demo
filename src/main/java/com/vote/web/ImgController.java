package com.vote.web;

import com.vote.util.ImgUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 功能说明: 图片控制器（单独拎出来）<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author cgw<br>
 * 开发时间: 2017年02月07日<br>
 */
@RestController
@RequestMapping(value = "/imgs")
public class ImgController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value="上传图片", notes="上传图片")
    @RequestMapping(value="/imgAdd/", method= RequestMethod.POST)
    public String postImg(@RequestParam MultipartFile img_file) {
        String img_path = "";
        try {
            //添加图片
            if(null != img_file && StringUtils.isNotBlank(img_file.getOriginalFilename())) {
                //转为图片路径
               img_path = ImgUtil.handleFileUpload(img_file, 100l);
            }
        } catch (Exception e) {
            logger.error("（ImgController.postImg）上传图片异常", e );
        }
        return img_path;
    }


}
