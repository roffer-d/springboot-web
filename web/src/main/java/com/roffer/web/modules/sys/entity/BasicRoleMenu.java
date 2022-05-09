package com.roffer.web.modules.sys.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色菜单
 * </p>
 *
 * @author Roffer
 * @since 2022-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="BasicRoleMenu对象", description="角色菜单")
public class BasicRoleMenu implements Serializable {

    private static final long serialVersionUID=1L;

      private String id;

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "菜单id")
    private String menuId;

    @ApiModelProperty(value = "权限类型(query、查询，add、新增，edit、编辑，delete、删除，import、导入，export、导出，examine、审核)")
    private String authorityType;


}
