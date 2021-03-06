package com.roffer.web.modules.sys.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author Roffer
 * @since 2022-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="BasicRole对象", description="角色")
public class BasicRole implements Serializable {

    private static final long serialVersionUID=1L;

      private String id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "创建时间")
      @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
