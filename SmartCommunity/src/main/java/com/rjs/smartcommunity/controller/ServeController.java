package com.rjs.smartcommunity.controller;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.common.Result;
import com.rjs.smartcommunity.entity.Serve;
import com.rjs.smartcommunity.service.ServeService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 社区服务表前端操作接口
 *
 * @author rjs
 */
@RestController
@RequestMapping("/serve")
public class ServeController {
    @Resource private ServeService serveService;

    /**
     * 新增社区服务信息
     *
     * @param serve 社区服务实体对象
     * @return 操作结果，成功返回success
     */
    @PostMapping("/add")
    public Result add(@RequestBody Serve serve) {
        serveService.add(serve);
        return Result.success();
    }

    /**
     * 根据ID删除社区服务信息
     *
     * @param id 社区服务ID
     * @return 操作结果，成功返回success
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        serveService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除社区服务信息
     *
     * @param ids 社区服务ID列表
     * @return 操作结果，成功返回success
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        serveService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 根据ID修改社区服务信息
     *
     * @param serve 社区服务实体对象
     * @return 操作结果，成功返回success
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Serve serve) {
        serveService.updateById(serve);
        return Result.success();
    }

    /**
     * 根据ID查询社区服务信息
     *
     * @param id 社区服务ID
     * @return 操作结果，包含查询到的社区服务信息
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Serve serve = serveService.selectById(id);
        return Result.success(serve);
    }

    /**
     * 查询所有社区服务信息
     *
     * @param serve 社区服务实体对象（可选条件）
     * @return 操作结果，包含所有社区服务信息列表
     */
    @GetMapping("/selectAll")
    public Result selectAll(Serve serve) {
        List<Serve> list = serveService.selectAll(serve);
        return Result.success(list);
    }

    /**
     * 分页查询社区服务信息
     *
     * @param serve 社区服务实体对象（可选条件）
     * @param pageNum 页码
     * @param pageSize 每页显示数量
     * @return 操作结果，包含分页查询到的社区服务信息
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            Serve serve,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Serve> page = serveService.selectPage(serve, pageNum, pageSize);
        return Result.success(page);
    }
}
