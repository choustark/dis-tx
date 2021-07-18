package com.chou.codegenerator_module;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.chou.codegenerator_module.bean.CustomizePath;
import com.chou.codegenerator_module.config.MyGeneratorInjectionConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CodeGeneratorModuleApplicationTests {

    public final static String basePath = "C:\\Users\\Axel\\Desktop\\";
    public final static String poPath = "com\\chou\\order_module\\po\\";
    public final static String voPath = "com\\chou\\order_module\\vo\\";
    public final static String searchPath = "com\\chou\\order_module\\po\\";

    @Test
    public void contextLoads() {
        AutoGenerator ag = new AutoGenerator();
        //全局配置
        GlobalConfig globalCfg = new GlobalConfig();
        globalCfg.setActiveRecord(false)
                .setOutputDir(basePath)
                .setAuthor("Chou")
                .setBaseResultMap(true)
                .setIdType(IdType.ID_WORKER)
                // 输出的时间格式
                .setDateType(DateType.ONLY_DATE);
        ag.setGlobalConfig(globalCfg);

        // 数据库配置
        DataSourceConfig dataSourceCfg = new DataSourceConfig();
        dataSourceCfg.setDbType(DbType.MYSQL)
                .setUrl("jdbc:mysql://192.168.68.115:3306/order_module?characterEncoding=UTF-8&useUnicode=true&useSSL=false")
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUsername("zhou")
                .setPassword("As123456;")
                .setTypeConvert(new MySqlTypeConvert() {
                    @Override
                    public IColumnType processTypeConvert(GlobalConfig globalConfig, TableField tableField) {
                        return super.processTypeConvert(globalConfig, tableField);
                    }
                });
        ag.setDataSource(dataSourceCfg);

        //模板路径配置
        TemplateConfig templateCfg = new TemplateConfig();
        templateCfg.setController("templates/Controller.java.vm" )
                .setEntity("templates/Entity.java.vm" )
                .setServiceImpl("templates/ServiceImpl.java.vm" )
                .setService("templates/Service.java.vm" )
                .setMapper("templates/Mapper.java.vm" )
                .setXml("templates/Mapper.xml.vm" );
        ag.setTemplate(templateCfg);

        //跟包相关的配置项
        PackageConfig packageCfg = new PackageConfig();
        packageCfg.setEntity("com.chou.order_module.domain.entity" )
                .setController("com.chou.order_module.controller" )
                .setMapper("com.chou.order_module.mapper" )
                .setService("com.chou.order_module.service" )
                .setXml("com.chou.order_module.mapper.xml" )
                .setServiceImpl("com.chou.order_module.service.impl" )
                .setParent(null);
        ag.setPackageInfo(packageCfg);

        //策略配置
        StrategyConfig strategyCfg = new StrategyConfig();
        strategyCfg.setNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true)
                .setEntityBooleanColumnRemoveIsPrefix(true)
                .setRestControllerStyle(true)
                .setEntityTableFieldAnnotationEnable(true);
        ag.setStrategy(strategyCfg);

        List<CustomizePath> paths = new ArrayList<>();
        paths.add(new CustomizePath().setSuffix("PO").setPrefix("Search").setTemplatePath("templates/SearchEntityPO.java.vm").setOutPutPath(basePath + searchPath));
        paths.add(new CustomizePath().setSuffix("VO").setPrefix("").setTemplatePath("templates/EntityVO.java.vm").setOutPutPath(basePath + voPath));
        paths.add(new CustomizePath().setSuffix("PO").setPrefix("").setTemplatePath("templates/EntityPO.java.vm").setOutPutPath(basePath + poPath));
        MyGeneratorInjectionConfig myGeneratorInjectionConfig = new MyGeneratorInjectionConfig(paths);
        ag.setCfg(myGeneratorInjectionConfig);
        ag.execute();
    }
}
