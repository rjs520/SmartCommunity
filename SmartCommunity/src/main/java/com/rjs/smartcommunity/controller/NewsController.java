package com.rjs.smartcommunity.controller;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.common.Result;
import com.rjs.smartcommunity.entity.News;
import com.rjs.smartcommunity.service.NewsService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.annotation.Resource;

/**
 * 咨询信息表前端操作接口控制器
 *
 * @author rjs
 */
@RestController
@RequestMapping("/news")
public class NewsController {

    /** 注入咨询信息服务 */
    @Resource private NewsService newsService;

    /**
     * 新增咨询信息接口
     *
     * @param news 待新增的咨询信息对象（News实体类）
     * @return Result 对象，表示操作成功
     */
    @PostMapping("/add")
    public Result add(@RequestBody News news) {
        newsService.add(news);
        return Result.success();
    }

    /**
     * 删除咨询信息接口（根据ID）
     *
     * @param id 咨询信息ID（Integer类型）
     * @return Result 对象，表示操作成功
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        newsService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除咨询信息接口
     *
     * @param ids 待删除的咨询信息ID列表（List<Integer>类型）
     * @return Result 对象，表示操作成功
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        newsService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新咨询信息接口（根据ID）
     *
     * @param news 待更新的咨询信息对象（News实体类）
     * @return Result 对象，表示操作成功
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody News news) {
        newsService.updateById(news);
        return Result.success();
    }

    /**
     * 根据ID查询咨询信息接口
     *
     * @param id 咨询信息ID（Integer类型）
     * @return Result 对象，包含查询到的咨询信息（News实体类）
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        News news = newsService.selectById(id);
        return Result.success(news);
    }

    /**
     * 查询所有咨询信息接口
     *
     * @param news 可选条件参数（News实体类，可为空）
     * @return Result 对象，包含所有咨询信息列表（List<News>类型）
     */
    @GetMapping("/selectAll")
    public Result selectAll(News news) {
        List<News> list = newsService.selectAll(news);
        return Result.success(list);
    }

    /**
     * 分页查询咨询信息接口
     *
     * @param news 可选条件参数（News实体类，可为空）
     * @param pageNum 当前页码（Integer类型，默认值1）
     * @param pageSize 每页大小（Integer类型，默认值10）
     * @return Result 对象，包含分页查询结果（PageInfo<News>类型）
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            News news,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<News> page = newsService.selectPage(news, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 从资讯服务中根据指定排序方式获取顶部资讯。
     *
     * @param sort 指定的排序方式，用于筛选顶部资讯。
     * @return 返回一个包含顶部资讯的列表，封装在Result对象中。如果操作成功，Result对象的status为200，data字段为资讯列表。
     */
    @GetMapping("/selectTopNews")
    public Result selectTopNews(@RequestParam String sort) {
        // 调用newsService中的selectTopNews方法，根据排序方式获取资讯列表
        List<News> list = newsService.selectTopNews(sort);
        // 将获取到的资讯列表封装在Result对象中，返回给前端
        return Result.success(list);
    }
}
