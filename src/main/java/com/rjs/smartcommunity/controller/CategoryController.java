package com.rjs.smartcommunity.controller;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.common.Result;
import com.rjs.smartcommunity.entity.Category;
import com.rjs.smartcommunity.service.CategoryService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.annotation.Resource;

/**
 * 资讯分类信息表前端操作接口控制器
 *
 * @author rjs
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    /** 注入资讯分类信息服务 */
    @Resource private CategoryService categoryService;

    /**
     * 新增资讯分类信息接口
     *
     * @param category 待新增的资讯分类信息对象（Category实体类）
     * @return Result 对象，表示操作成功
     */
    @PostMapping("/add")
    public Result add(@RequestBody Category category) {
        categoryService.add(category);
        return Result.success();
    }

    /**
     * 删除资讯分类信息接口（根据ID）
     *
     * @param id 资讯分类信息ID（Integer类型）
     * @return Result 对象，表示操作成功
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        categoryService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除资讯分类信息接口
     *
     * @param ids 待删除的资讯分类信息ID列表（List<Integer>类型）
     * @return Result 对象，表示操作成功
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        categoryService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新资讯分类信息接口（根据ID）
     *
     * @param category 待更新的资讯分类信息对象（Category实体类）
     * @return Result 对象，表示操作成功
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Category category) {
        categoryService.updateById(category);
        return Result.success();
    }

    /**
     * 根据ID查询资讯分类信息接口
     *
     * @param id 资讯分类信息ID（Integer类型）
     * @return Result 对象，包含查询到的资讯分类信息（Category实体类）
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Category category = categoryService.selectById(id);
        return Result.success(category);
    }

    /**
     * 查询所有资讯分类信息接口
     *
     * @param category 可选条件参数（Category实体类，可为空）
     * @return Result 对象，包含所有资讯分类信息列表（List<Category>类型）
     */
    @GetMapping("/selectAll")
    public Result selectAll(Category category) {
        List<Category> list = categoryService.selectAll(category);
        return Result.success(list);
    }

    /**
     * 分页查询资讯分类信息接口
     *
     * @param category 可选条件参数（Category实体类，可为空）
     * @param pageNum 当前页码（Integer类型，默认值1）
     * @param pageSize 每页大小（Integer类型，默认值10）
     * @return Result 对象，包含分页查询结果（PageInfo<Category>类型）
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            Category category,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Category> page = categoryService.selectPage(category, pageNum, pageSize);
        return Result.success(page);
    }
}
