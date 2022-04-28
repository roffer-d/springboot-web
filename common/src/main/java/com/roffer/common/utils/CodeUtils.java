package com.roffer.common.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author roffer
 */
public class CodeUtils {
    private String jdbcUrl;
    private String jdbcDriverClassName;
    private String jdbcUser;
    private String jdbcPwd;
    private String parent;
    private String outputDir;
    private String outputDirXml;
    private Boolean generateVue;
    private String vueOutputPath;
    private String tabNames;
    private String author;
    private String insertTableFill;
    private String updateTableFill;

    /**
      * @description 构造函数，指定具体参数
      * @params:
      *   jdbcUrl(String): 数据库连接地址
      *   jdbcDriverClassName(String): 数据库驱动类名
      *   jdbcUser(String): 数据库登录账号
      *   jdbcPwd(String): 数据库登录密码
      *   parent(String): 指定生成的包名
      *   outputDir(String): 指定输出java文件输出目录
      *   outputDirXml(String): 指定输出mapper文件输出目录
      *   vueOutputPath(String): 指定输出vue文件输出目录
      *   tabNames(String): 需要生成的数据库表名，多个逗号隔开
      *   author(String): 作者姓名
      *   insertTableFill(String): 保存数据时自动填充时间字段
      *   updateTableFill(String): 更新数据时自动填充时间字段
      * @author Dulongfei
      * @date 2022/4/7 13:17
      */
    public CodeUtils(
            String jdbcUrl, String jdbcDriverClassName, String jdbcUser, String jdbcPwd,
            String parent, String outputDir, String outputDirXml, Boolean generateVue,
            String vueOutputPath, String tabNames, String author,
            String insertTableFill,String updateTableFill){

        this.jdbcUrl = jdbcUrl;
        this.jdbcDriverClassName = jdbcDriverClassName;
        this.jdbcUser = jdbcUser;
        this.jdbcPwd = jdbcPwd;
        this.parent = parent;
        this.author = author;
        this.outputDir = outputDir;
        this.outputDirXml = outputDirXml;
        this.generateVue = generateVue;
        this.vueOutputPath = vueOutputPath;
        this.tabNames = tabNames;
        this.insertTableFill = insertTableFill;
        this.updateTableFill = updateTableFill;
        this.tabNames = tabNames;
    }

    /**
      * @description 构造方法，传入一个yml或者.properties文件
      *    文件中包含以下字段：
      *   jdbcUrl(String): 数据库连接地址
      *   jdbcDriverClassName(String): 数据库驱动类名
      *   jdbcUser(String): 数据库登录账号
      *   jdbcPwd(String): 数据库登录密码
      *   parent(String): 指定生成的包名
      *   outputDir(String): 指定输出java文件输出目录
      *   outputDirXml(String): 指定输出mapper文件输出目录
      *   tabNames(String): 需要生成的数据库表名，多个逗号隔开
      *   author(String): 作者姓名
      *   insertTableFill(String): 保存数据时自动填充时间字段
      *   updateTableFill(String): 更新数据时自动填充时间字段
      *
      * @params:
      *   inputStream(InputStream): 文件流
      * @author Dulongfei
      * @date 2022/4/7 13:29
      */
    public CodeUtils(InputStream inputStream){
        Properties pros = new Properties();
        try {
            pros.load(new InputStreamReader(inputStream, "UTF-8"));
            this.jdbcUrl = pros.getProperty("jdbcUrl");
            this.jdbcDriverClassName = pros.getProperty("jdbcDriverClassName");
            this.jdbcUser = pros.getProperty("jdbcUser");
            this.jdbcPwd = pros.getProperty("jdbcPwd");
            this.parent = pros.getProperty("parent");
            this.author = pros.getProperty("author");
            this.outputDir = pros.getProperty("outputDir");
            this.outputDirXml = pros.getProperty("outputDirXml");
            this.generateVue = "true".equalsIgnoreCase(pros.getProperty("generateVue"));
            this.vueOutputPath = pros.getProperty("vueOutputPath");
            this.tabNames = pros.getProperty("tabNames");
            this.insertTableFill = pros.getProperty("insertTableFill");
            this.updateTableFill = pros.getProperty("updateTableFill");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doGenerator(){
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setOutputDir(outputDir);
        gc.setAuthor(author);
        gc.setOpen(false);
        // 设置名字
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        // 设置 resultMap
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setFileOverride(true);
        gc.setSwagger2(true);
        gc.setDateType(DateType.ONLY_DATE);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(jdbcUrl);
        dsc.setDriverName(jdbcDriverClassName);
        dsc.setUsername(jdbcUser);
        dsc.setPassword(jdbcPwd);
        mpg.setDataSource(dsc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 包配置
        PackageConfig pc = new PackageConfig();
        //  pc.setModuleName(scanner("模块名"));
        pc.setParent(parent);
        mpg.setPackageInfo(pc);


        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();

        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return outputDirXml + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        if(this.generateVue){
            String templateListPath = "/templates/list.vue.vm";
            String templateEditPath = "/templates/edit.vue.vm";
            String templateApiPath = "/templates/api.js.vm";
            String templateRouterPath = "/templates/router.js.vm";

            /** 生成前端列表页面 **/
            focList.add(new FileOutConfig(templateListPath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名
                    return vueOutputPath + "/" + tableInfo.getEntityPath() + "/list.vue";
                }
            });

            /** 生成前端编辑页面 **/
            focList.add(new FileOutConfig(templateEditPath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名
                    return vueOutputPath + "/" + tableInfo.getEntityPath() + "/components/edit.vue";
                }
            });

            /** 生成前端api接口请求js文件 **/
            focList.add(new FileOutConfig(templateApiPath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名
                    return vueOutputPath + "/" + tableInfo.getEntityPath() + "/api.js";
                }
            });

            /** 生成前端路由js文件 **/
            focList.add(new FileOutConfig(templateRouterPath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名
                    return vueOutputPath + "/" + tableInfo.getEntityPath() + "/router.js";
                }
            });
        }

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
//        templateConfig.setService("/templates/service.java.vm");
//        templateConfig.setServiceImpl("/templates/serviceImpl.java.vm");
//        templateConfig.setEntity(null);
//        templateConfig.setMapper("/templates/mapper.java.vm");
//        templateConfig.setController(null);
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);

        // 自动填充
        ArrayList<TableFill> tableFills = new ArrayList<>();
        if(null != this.insertTableFill){
            TableFill gmtCreate = new TableFill(this.insertTableFill, FieldFill.INSERT);
            tableFills.add(gmtCreate);
        }
        if(null != this.updateTableFill){
            TableFill gmtModified = new TableFill(this.updateTableFill, FieldFill.INSERT_UPDATE);
            tableFills.add(gmtModified);
        }
        strategy.setTableFillList(tableFills);

        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
        strategy.setInclude(tabNames.split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }
}