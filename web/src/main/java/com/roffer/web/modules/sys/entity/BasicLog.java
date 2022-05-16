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
 * 操作日志
 * </p>
 *
 * @author Roffer
 * @since 2022-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="BasicLog对象", description="操作日志")
public class BasicLog implements Serializable {

    private static final long serialVersionUID=1L;

      private String id;

    @ApiModelProperty(value = "操作描述")
    private String remark;

    @ApiModelProperty(value = "操作用户id")
    private String userId;

    @ApiModelProperty(value = "操作用户")
    private String userName;

    @ApiModelProperty(value = "操作时间")
      @TableField(fill = FieldFill.INSERT)
    private Date createTime;


}
