package com.rjs.smartcommunity.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 楼宇实体类
 *
 * @author rjs
 */
@Setter
@Getter
public class Building {
    /** ID */
    private Integer id;

    /** 楼宇名称 */
    private String name;

    /** 地址 */
    private String address;

    /** 总楼层数 */
    private Integer totalFloors;

    /** 总户数 */
    private Integer totalHouses;

    /** 物业公司 */
    private String propertyCompany;

    /** 物业电话 */
    private String propertyPhone;
}
