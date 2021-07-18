package com.chou.codegenerator_module.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName CustomizePath
 * @Description TODO
 * @Author Axel
 * @Date 2021/7/18 16:12
 * @Version 1.0
 */

@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class CustomizePath {
    /**
     * 自定义文件后缀
     */
    private String suffix;
    /**
     * 自定义文件模本路径
     */
    private String templatePath;
    /**
     * 自定义文件输出的路径
     */
    private String outPutPath;
    /**
     *  自定义文件名前缀
     */
    private String prefix;
}
