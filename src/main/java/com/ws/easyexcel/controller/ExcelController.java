package com.ws.easyexcel.controller;

import com.ws.easyexcel.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * excel控制器
 *
 * @author wangsen
 * @date 2024/05/19
 */
@RestController
@RequestMapping("/excel")
@RequiredArgsConstructor
public class ExcelController {

    private final BookService bookService;

    @GetMapping("/export")
    public void exportExcel(HttpServletResponse response) throws IOException {
        bookService.export(response);
    }


}
