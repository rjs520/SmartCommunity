package com.rjs.smartcommunity.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;

import com.rjs.smartcommunity.common.Result;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

/**
 * 文件接口控制器
 *
 * @author rjs
 */
@RestController
@RequestMapping("/files")
public class FileController {

    /** 文件上传存储路径，默认为应用运行目录下的"/files/"子目录。 */
    private static final String FILE_PATH = System.getProperty("user.dir") + "/files/";

    /** 服务器端口，从配置文件中读取，若未配置则默认为9090。 */
    @Value("${server.port:9090}")
    private String port;

    /** 服务器IP地址，从配置文件中读取，若未配置则默认为"localhost"。 */
    @Value("${ip:localhost}")
    private String ip;

    /**
     * 文件上传接口
     *
     * @param file 上传的文件对象（MultipartFile类型）
     * @return Result 对象，包含上传成功后的文件访问URL
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) {
        String flag;
        synchronized (FileController.class) {
            flag = System.currentTimeMillis() + "";
            ThreadUtil.sleep(1L);
        }
        String fileName = file.getOriginalFilename();
        try {
            if (!FileUtil.isDirectory(FILE_PATH)) {
                FileUtil.mkdir(FILE_PATH);
            }
            // 文件存储形式：时间戳-文件名
            FileUtil.writeBytes(
                    file.getBytes(),
                    FILE_PATH + flag + "-" + fileName);
            System.out.println(fileName + "--上传成功");

        } catch (Exception e) {
            System.err.println(fileName + "--文件上传失败");
        }
        String http = "http://" + ip + ":" + port + "/files/";
        return Result.success(
                http + flag + "-"
                        + fileName);
    }

    /**
     * 处理富文本文件上传请求。
     *
     * @param file 用户上传的文件，通过MultipartFile接收。
     * @return 返回一个包含上传文件信息的Dict对象。
     */
    @PostMapping("/editor/upload")
    public Dict editorUpload(MultipartFile file) {
        String flag;
        // 使用同步代码块，确保线程安全
        synchronized (FileController.class) {
            flag = System.currentTimeMillis() + "";
            ThreadUtil.sleep(1L);
        }
        String fileName = file.getOriginalFilename();
        try {
            // 检查上传目录是否存在，不存在则创建
            if (!FileUtil.isDirectory(FILE_PATH)) {
                FileUtil.mkdir(FILE_PATH);
            }
            // 构造文件保存路径：时间戳-文件名
            FileUtil.writeBytes(file.getBytes(), FILE_PATH + flag + "-" + fileName);
            System.out.println(fileName + "--上传成功");

        } catch (Exception e) {
            System.err.println(fileName + "--文件上传失败");
        }
        // 构造文件访问URL
        String http = "http://" + ip + ":" + port + "/files/";
        // 返回包含文件URL的Dict对象
        return Dict.create()
                .set("errno", 0)
                .set(
                        "data",
                        CollUtil.newArrayList(
                                Dict.create().set("url", http + flag + "-" + fileName)));
    }

    /**
     * 获取文件接口
     *
     * @param flag 文件标识（由上传时的时间戳与原始文件名组合而成）
     * @param response HttpServletResponse对象，用于设置响应头和输出文件内容
     * @throws Exception 若文件不存在或读取失败，抛出异常
     */
    @GetMapping("/{flag}")
    public void avatarPath(@PathVariable String flag, HttpServletResponse response)
            throws Exception {
        OutputStream os;
        if (StrUtil.isNotEmpty(flag)) {
            response.addHeader(
                    "Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(flag, "UTF-8"));
            response.setContentType("application/octet-stream");
            byte[] bytes = FileUtil.readBytes(FILE_PATH + flag);
            os = response.getOutputStream();
            os.write(bytes);
            os.flush();
            os.close();
        }
    }

    /**
     * 删除文件接口
     *
     * @param flag 文件标识（由上传时的时间戳与原始文件名组合而成）
     */
    @DeleteMapping("/{flag}")
    public void delFile(@PathVariable String flag) {
        FileUtil.del(FILE_PATH + flag);
        System.out.println("删除文件" + flag + "成功");
    }
}
