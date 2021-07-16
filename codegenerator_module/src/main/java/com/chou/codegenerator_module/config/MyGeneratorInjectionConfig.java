package com.chou.codegenerator_module.config;

import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MyGeneratorInjectionConfig
 * @Description 自定义mybatisPlus 代码生成对的注入配置
 * @Author Axel
 * @Date 2021/7/4 17:05
 * @Version 1.0
 */

public class MyGeneratorInjectionConfig extends InjectionConfig {
    public String filePath;

    public MyGeneratorInjectionConfig() {
    }

    public MyGeneratorInjectionConfig(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void initMap() {
        Map<String, Object> map = super.getMap();
        List<FileOutConfig> list = new ArrayList<>();
        list.add(new FileOutConfig("templates/EntityPO.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return filePath + tableInfo.getEntityName();
            }
        });
        super.setFileOutConfigList(list);
    }
}
