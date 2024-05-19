package com.ws.easyexcel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ws.easyexcel.entity.po.Book;
import org.apache.ibatis.annotations.Mapper;

/**
 * 书映射器
 *
 * @author wangsen
 * @date 2024/05/19
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {
}
