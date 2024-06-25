package com.ws.easyexcel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 作者
 *
 * @author wangsen
 * @date 2024/06/25
 */
@Data
@TableName("author")
public class Author {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 名字
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

}
