package com.vote.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by cgw on 2017/2/9.
 */
public class ImgUtil {

    /**
     * 文件上传处理
     * @param mfile
     * @param vote_id
     * @return
     * @throws Exception
     */
    public static String handleFileUpload(MultipartFile mfile, Long vote_id) throws Exception {

        if (!mfile.isEmpty()) {
            String filename = mfile.getOriginalFilename();
            String file_extname = filename.substring(filename.lastIndexOf("."), filename.length());
            filename = vote_id + "_" +  new Date().getTime();
            String path = getRuntimeConfigPath() + File.separator + filename + file_extname;
            try {
                mfile.transferTo(new File(path));
                path =  "http://139.129.47.102/hyhimg/" + filename + file_extname;        //转为http地址
                return path;
            } catch (IllegalStateException | IOException e) {
                throw new Exception(e);
            }
        }
        return null;
    }

    /**
     * 获取系统运行期配置文件路径
     * @return
     */
    public static String getRuntimeConfigPath() {
        String path = System.getenv().get("RUNTIME_CONFIG_ROOT");
        if (StringUtils.isBlank(path)) {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.indexOf("win") >= 0) {
                path = "E:"+File.separator+"votefile"+File.separator+"hyhimg";
            }
            else {
                path = "/usr/cgw/votefile/hyhimg";
            }
        }
        return path;
    }
}
