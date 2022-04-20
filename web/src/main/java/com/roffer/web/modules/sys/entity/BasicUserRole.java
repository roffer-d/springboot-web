package com.roffer.web.modules.sys.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户角色
 * </p>
 *
 * @author Roffer
 * @since 2022-04-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="BasicUserRole对象", description="用户角色")
public class BasicUserRole implements Serializable {

    private static final long serialVersionUID=1L;

      private Long id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;


}
