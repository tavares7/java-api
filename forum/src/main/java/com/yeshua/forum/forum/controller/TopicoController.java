package com.yeshua.forum.forum.controller;

import java.util.Arrays;
import java.util.List;

import com.yeshua.forum.forum.controller.dto.TopicoDto;
import com.yeshua.forum.forum.model.Curso;
import com.yeshua.forum.forum.model.Topico;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicoController {

    @RequestMapping("/topicos")
    @ResponseBody
    public List<TopicoDto> topicos() {
        return TopicoDto.converter(Arrays.asList(new Topico("T1", "m1", new Curso("C1", "D1"))));
    }
}
