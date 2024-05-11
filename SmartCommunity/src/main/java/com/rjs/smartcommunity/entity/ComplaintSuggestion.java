package com.rjs.smartcommunity.entity;

import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 投诉与建议实体类
 *
 * @author rjs
 */
@Setter
@Getter
public class ComplaintSuggestion implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;

    /** 标题 */
    private String name;

    /** 封面 */
    private String cover;

    /** 描述 */
    private String descr;

    /** 日期 */
    private String date;

    /** 状态 */
    private String status;
}
