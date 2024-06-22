package com.ws.easyexcel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.easyexcel.entity.po.Book;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


/**
 * 书服务
 *
 * @author wangsen
 * @date 2024/05/19
 */
public interface BookService extends IService<Book> {

    void export(HttpServletResponse response) throws IOException;

}
