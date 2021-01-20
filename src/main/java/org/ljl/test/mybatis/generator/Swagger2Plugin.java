package org.ljl.test.mybatis.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * @auther: lvjinglu
 * @date: 2020/12/29 20:58
 * @description:
 */
public class Swagger2Plugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
//        String classAnnotation = "@ApiModel(value=\"" + introspectedTable.getRemarks()  + "\")";
//        if(!topLevelClass.getAnnotations().contains(classAnnotation)) {
//            topLevelClass.addAnnotation(classAnnotation);
//        }

//        String apiModelAnnotationPackage =  properties.getProperty("apiModelAnnotationPackage");
        String apiModelPropertyAnnotationPackage = properties.getProperty("apiModelPropertyAnnotationPackage");
//        if(null == apiModelAnnotationPackage) apiModelAnnotationPackage = "io.swagger.annotations.ApiModel";
        if(null == apiModelPropertyAnnotationPackage) apiModelPropertyAnnotationPackage = "io.swagger.annotations.ApiModelProperty";

//        topLevelClass.addImportedType(apiModelAnnotationPackage);
        topLevelClass.addImportedType(apiModelPropertyAnnotationPackage);

        String remarks = introspectedColumn.getRemarks();
        if (remarks != null && remarks.length() > 0) {
            field.addAnnotation("@ApiModelProperty(value=\"" + remarks + "\")");
        }
        return super.modelFieldGenerated(field, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
    }
}
