package com.rjs.smartcommunity.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 车位实体类
 *
 * @author rjs
 */
@Setter
@Getter
public class Parking {
    /** 车位ID */
    private Integer id;

    /** 车位所在地点 */
    private String address;

    /** 车位描述信息 */
    private String descr;

    /** 车位位置图片的URL或路径 */
    private String img;

    /** 车位信息最后更新时间 */
    private String time;
}
