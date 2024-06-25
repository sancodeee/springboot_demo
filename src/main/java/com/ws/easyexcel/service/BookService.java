package com.ws.easyexcel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.easyexcel.entity.bo.BookListBO;
import com.ws.easyexcel.entity.po.Book;
import com.ws.easyexcel.entity.vo.BookListVO;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


/**
 * 书服务
 *
 * @author wangsen
 * @date 2024/05/19
 */
public interface BookService extends IService<Book> {

    /**
     * excel导出
     *
     * @param response 响应
     * @throws IOException ioexception
     */
    void export(HttpServletResponse response) throws IOException;

    /**
     * 查询图书列表
     *
     * @param bookListBO 书单
     * @return {@link List }<{@link Book }>
     */
    List<BookListVO> queryBookList(BookListBO bookListBO);


}
