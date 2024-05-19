package com.ws.easyexcel.entity.po;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 书籍信息实体类
 *
 * @author wangsen
 * @date 2024/05/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@HeadRowHeight(20)
@ContentRowHeight(20)
@TableName("book")
public class Book {

    /**
     * id
     */
    @ExcelProperty(value = {"书籍信息", "id"}, index = 0)
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 名字
     */
    @ColumnWidth(20)
    @ExcelProperty(value = {"书籍信息", "书籍名称"}, index = 1)
    private String name;

    /**
     * 类型
     */
    @ColumnWidth(10)
    @ExcelProperty(value = {"书籍信息", "书籍类型"}, index = 2)
    private String type;

    /**
     * 描述
     */
    @ColumnWidth(50)
    @ExcelProperty(value = {"书籍信息", "书籍描述"}, index = 3)
    private String description;

}
