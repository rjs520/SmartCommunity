package com.rjs.smartcommunity.controller;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.common.Result;
import com.rjs.smartcommunity.entity.User;
import com.rjs.smartcommunity.service.UserService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.annotation.Resource;

/**
 * 管理员前端操作接口控制器，提供RESTful API以供前端进行管理员的增删改查及分页查询操作。
 *
 * @author rjs
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /** 注入管理员服务对象，用于处理与管理员相关的业务逻辑。 */
    @Resource private UserService userService;

    /**
     * 新增管理员接口。 请求方式：POST 请求地址：/user/add 请求体：JSON格式的{@link User}对象
     *
     * @param user 待新增的管理员对象，包含所需的所有属性信息
     * @return 返回表示操作结果的{@link Result}对象，状态码为"200"表示成功
     */
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        userService.add(user);
        return Result.success();
    }

    /**
     * 根据ID删除管理员接口。 请求方式：DELETE 请求地址：/user/delete/{id} URL参数：{id} - 要删除的管理员ID
     *
     * @param id 要删除的管理员ID
     * @return 返回表示操作结果的{@link Result}对象，状态码为"200"表示成功
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除管理员接口。 请求方式：DELETE 请求地址：/user/delete/batch 请求体：JSON格式的管理员ID列表
     *
     * @param ids 要批量删除的管理员ID列表
     * @return 返回表示操作结果的{@link Result}对象，状态码为"200"表示成功
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        userService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新管理员接口。 请求方式：PUT 请求地址：/user/update 请求体：JSON格式的更新后的{@link User}对象
     *
     * @param user 更新后的管理员对象，包含需要修改的属性信息
     * @return 返回表示操作结果的{@link Result}对象，状态码为"200"表示成功
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody User user) {
        userService.updateById(user);
        return Result.success();
    }

    /**
     * 根据ID查询管理员接口。 请求方式：GET 请求地址：/user/selectById/{id} URL参数：{id} - 要查询的管理员ID
     *
     * @param id 要查询的管理员ID
     * @return 返回表示操作结果的{@link Result}对象，状态码为"200"，数据部分包含查询到的管理员对象
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        User user = userService.selectById(id);
        return Result.success(user);
    }

    /**
     * 查询所有管理员接口。 请求方式：GET 请求地址：/user/selectAll 请求参数（可选）：传入一个{@link User}对象作为查询条件，可包含部分属性信息
     *
     * @param user 可选的查询条件对象，包含部分或全部属性信息
     * @return 返回表示操作结果的{@link Result}对象，状态码为"200"，数据部分包含查询到的管理员列表
     */
    @GetMapping("/selectAll")
    public Result selectAll(User user) {
        List<User> list = userService.selectAll(user);
        return Result.success(list);
    }

    /**
     * 分页查询管理员接口。 请求方式：GET 请求地址：/user/selectPage 请求参数（可选）： - 查询条件对象（{@link User}），包含部分或全部属性信息 -
     * pageNum：当前页码，默认为1 - pageSize：每页记录数，默认为10
     *
     * @param user 可选的查询条件对象，包含部分或全部属性信息
     * @param pageNum 当前页码
     * @param pageSize 每页记录数
     * @return 返回表示操作结果的{@link Result}对象，状态码为"200"，数据部分包含分页查询结果的封装对象（{@link PageInfo}）
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            User user,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<User> page = userService.selectPage(user, pageNum, pageSize);
        return Result.success(page);
    }
}
