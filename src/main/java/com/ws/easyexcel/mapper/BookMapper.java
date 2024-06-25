package com.ws.easyexcel.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ws.easyexcel.entity.bo.BookListBO;
import com.ws.easyexcel.entity.po.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 书映射器
 *
 * @author wangsen
 * @date 2024/05/19
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {

    List<Book> selectBooksList(BookListBO bookListBO, @Param(Constants.WRAPPER) Wrapper<Book> wrapper);

}
