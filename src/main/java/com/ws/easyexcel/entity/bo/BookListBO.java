package com.ws.easyexcel.entity.bo;

import lombok.Data;

/**
 * 书籍列表查询入参
 *
 * @author wangsen
 * @date 2024/06/25
 */
@Data
public class BookListBO {

    /**
     * 名字
     */
    private String name;

    /**
     * 类型
     */
    private String type;

    /**
     * 描述
     */
    private String description;

    /**
     * 作者
     */
    private String author;

}
