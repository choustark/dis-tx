package com.chou.codegenerator_module.config;

import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.chou.codegenerator_module.bean.CustomizePath;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @ClassName MyGeneratorInjectionConfig
 * @Description 自定义mybatisPlus 代码生成对的注入配置
 * @Author Axel
 * @Date 2021/7/4 17:05
 * @Version 1.0
 */

public class MyGeneratorInjectionConfig extends InjectionConfig {

    private List<CustomizePath> customizePaths;

    public MyGeneratorInjectionConfig() {
    }

    public MyGeneratorInjectionConfig(List<CustomizePath> customizePaths) {
        this.customizePaths = customizePaths;
    }

    @Override
    public void initMap() {
        Map<String, Object> map = new HashMap<>();
        super.setMap(map);
        List<FileOutConfig> list = new ArrayList<>();
        for (CustomizePath path: customizePaths) {
            list.add(new FileOutConfig(path.getTemplatePath()) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return path.getOutPutPath() + path.getPrefix() + tableInfo.getEntityName() + path.getSuffix() + ".java";
                }
            });
            if (StringUtils.isEmpty(path.getSuffix().toLowerCase())){
                throw new RuntimeException("自定义实体类缺少必要参数...");
            }
            map.put(path.getSuffix().toLowerCase() + "Pack", path.getSuffix().toLowerCase());
        }
        super.setFileOutConfigList(list);
    }

    public void setPathMap(List<CustomizePath> customizePaths) {
        this.customizePaths = customizePaths;
    }
}
