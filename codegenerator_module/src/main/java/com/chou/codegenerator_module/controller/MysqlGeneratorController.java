package com.chou.codegenerator_module.controller;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.chou.codegenerator_module.po.GeneratorPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @ClassName MysqlGeneratorController
 * @Description TODO
 * @Author Axel
 * @Date 2021/3/23 10:22
 * @Version 1.0
 */

@RestController("/mysql")
@Slf4j
public class MysqlGeneratorController {

    @GetMapping("/execute")
    public void generatorMysqlCode(GeneratorPo po){
        AutoGenerator ag = new AutoGenerator();

        //全局配置
        GlobalConfig globalCfg = new GlobalConfig();
        globalCfg.setActiveRecord(false);
        globalCfg.setOutputDir("C:\\Users\\Axel\\Desktop");
        globalCfg.setAuthor("Chou");
        globalCfg.setBaseResultMap(true);
        globalCfg.setIdType(IdType.ID_WORKER);
        ag.setGlobalConfig(globalCfg);

        // 数据库配置
        DataSourceConfig dataSourceCfg = new DataSourceConfig();
        dataSourceCfg.setTypeConvert(new MySqlTypeConvert(){
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, TableField tableField) {
                return super.processTypeConvert(globalConfig,tableField);
            }
        });
        dataSourceCfg.setDbType(DbType.MYSQL);
        dataSourceCfg.setUrl("jdbc:mysql://192.168.68.115:3306/order_module?characterEncoding=UTF-8&useUnicode=true&useSSL=false");
        dataSourceCfg.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceCfg.setUsername("zhou");
        dataSourceCfg.setPassword("As123456;");
        ag.setDataSource(dataSourceCfg);

        //模板路径配置
        TemplateConfig templateCfg = new TemplateConfig();
        templateCfg.setController("templates/myController.java.vm");
        templateCfg.setEntity("templates/myEntity.java.vm");
        templateCfg.setService("templates/myService.java.vm");
        templateCfg.setServiceImpl("templates/myServiceImpl.java.vm");
        templateCfg.setMapper("templates/myMapper.java.vm");
        templateCfg.setXml("templates/myMapper.xml.vm");
        ag.setTemplate(templateCfg);

        //跟包相关的配置项
        PackageConfig packageCfg = new PackageConfig();
        packageCfg.setEntity("com.chou.order_module.domain.entity");
        packageCfg.setController("com.chou.order_module.controller");
        packageCfg.setMapper("com.chou.order_module.mapper");
        packageCfg.setService("com.chou.order_module.service");
        packageCfg.setXml("com.chou.order_module.mapper.mapper");
        packageCfg.setServiceImpl("com.chou.order_module.service.impl");
        packageCfg.setParent(null);
        ag.setPackageInfo(packageCfg);

        //策略配置
        StrategyConfig strategyCfg = new StrategyConfig();
        strategyCfg.setNaming(NamingStrategy.underline_to_camel);
        strategyCfg.setEntityLombokModel(true);
        strategyCfg.setEntityBooleanColumnRemoveIsPrefix(true);
        strategyCfg.setEntityTableFieldAnnotationEnable(true);
        strategyCfg.setRestControllerStyle(true);
        ag.setStrategy(strategyCfg);

        InjectionConfig icf = new InjectionConfig() {
            @Override
            public void initMap() {
                HashMap<String,Object> map = new HashMap<>();
                map.put("",this.prepareObjectMap(map));
                this.setMap(map);
            }
        };
        ag.execute();
    }
}
