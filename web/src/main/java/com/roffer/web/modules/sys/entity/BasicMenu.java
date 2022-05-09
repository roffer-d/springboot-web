package com.roffer.web.modules.sys.entity;

import java.util.ArrayList;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author Roffer
 * @since 2022-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "BasicMenu对象", description = "菜单")
public class BasicMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty(value = "直属父级id")
    private String pid;

    @ApiModelProperty(value = "直属父级名称")
    private String pname;

    @ApiModelProperty(value = "所有父级id")
    private String pids;

    @ApiModelProperty(value = "所有父级名称")
    private String pnames;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "路由")
    private String route;

    @ApiModelProperty(value = "拥有权限")
    private String auth;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "描述")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
