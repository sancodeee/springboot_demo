package com.ws.easyexcel.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ws.easyexcel.entity.bo.BookListBO;
import com.ws.easyexcel.entity.po.Book;
import com.ws.easyexcel.entity.vo.BookListVO;
import com.ws.easyexcel.mapper.BookMapper;
import com.ws.easyexcel.service.BookService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 书服务实现
 *
 * @author wangsen
 * @date 2024/05/19
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Override
    public void export(HttpServletResponse response) throws IOException {

        // 设置响应头
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("书籍信息数据", StandardCharsets.UTF_8);
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        // 使用easyExcel写入
        // 写法一
        // EasyExcel.write(response.getOutputStream(), Book.class).sheet("书籍信息数据").doWrite(bookList);

        // 写法二
        // 查询数据库
        EasyExcel.write(response.getOutputStream(), Book.class).sheet("书籍信息数据").doWrite(this::list);
    }

    /**
     * 查询图书列表
     *
     * @param bookListBO 书单
     * @return {@link List }<{@link Book }>
     */
    @Override
    public List<BookListVO> queryBookList(BookListBO bookListBO) {

        // 构造条件查询，支持常规QueryWrapper
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(bookListBO.getAuthorId()), "author_id", bookListBO.getAuthorId());

        // 同样支持LambdaQueryWrapper
        LambdaQueryWrapper<Book> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 保证sql始终有where关键字
        lambdaQueryWrapper.apply("1 = 1");
        lambdaQueryWrapper.eq(StringUtils.isNotBlank(bookListBO.getAuthorId()), Book::getAuthorId, bookListBO.getAuthorId());
        // 传入查询条件
        return baseMapper.selectBooksList(bookListBO, lambdaQueryWrapper);
    }

}
