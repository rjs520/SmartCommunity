package com.rjs.smartcommunity.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.io.Serial;
import lombok.Getter;
import lombok.Setter;

/**
 * 资讯实体类，用于封装资讯相关的数据信息。 实现了Serializable接口，支持序列化与反序列化操作，便于对象在网络间传输或存储到文件中。
 *
 * @author rjs
 */
@Getter
@Setter
@Schema(description = "资讯实体类")
public class News implements Serializable {

    /** 序列化ID，用于确保类的兼容性，即使类的结构发生改变，依然能够成功反序列化之前的对象。 */
    @Serial private static final long serialVersionUID = 1L;

    /** 资讯的唯一标识ID。 */
    @Schema(description = "资讯的唯一标识ID", example = "1")
    private Integer id;

    /** 资讯的标题，简要概述资讯内容。 */
    @Schema(description = "资讯的标题", example = "新闻标题")
    private String title;

    /** 资讯的简介，对资讯内容进行简短描述。 */
    @Schema(description = "资讯的简介", example = "新闻简介")
    private String descr;

    /** 资讯的详细内容。 */
    @Schema(description = "资讯的详细内容", example = "新闻内容")
    private String content;

    /** 资讯的封面图片路径或URL，用于展示在列表或详情页面。 */
    @Schema(description = "资讯的封面图片路径或URL", example = "/images/news.jpg")
    private String cover;

    /** 资讯的发布时间，通常为日期时间字符串格式。 */
    @Schema(description = "资讯的发布时间", example = "2021-01-01 12:00:00")
    private String time;

    /** 资讯被浏览的次数，记录用户访问量。 */
    @Schema(description = "资讯被浏览的次数", example = "100")
    private Integer count;

    /** 资讯所属的分类，如新闻、公告、活动等。 */
    @Schema(description = "资讯所属的分类", example = "news")
    private String category;
}
