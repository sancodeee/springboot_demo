package com.ws.easyexcel.entity.vo;

import lombok.Data;

/**
 * 书单vo
 *
 * @author wangsen
 * @date 2024/06/25
 */
@Data
public class BookListVO {

    /**
     * id
     */
    private Long id;

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
    private String authorName;

}
