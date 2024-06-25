package com.ws.easyexcel.controller;

import com.ws.common.result.Result;
import com.ws.easyexcel.entity.bo.BookListBO;
import com.ws.easyexcel.entity.po.Book;
import com.ws.easyexcel.entity.vo.BookListVO;
import com.ws.easyexcel.service.BookService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 书籍控制器
 *
 * @author wangsen
 * @date 2024/06/25
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bookService;

    /**
     * 获取图书列表
     *
     * @param bookListBO 书单
     * @return {@link Result }<{@link List }<{@link Book }>>
     */
    @PostMapping("getBookList")
    Result<List<BookListVO>> getBookList(@RequestBody BookListBO bookListBO) {
        List<BookListVO> books = bookService.queryBookList(bookListBO);
        return Result.success(books);
    }

}
