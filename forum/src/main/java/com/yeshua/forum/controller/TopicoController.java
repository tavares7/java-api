package com.yeshua.forum.controller;

import java.util.List;

import com.yeshua.forum.controller.dto.TopicoDto;
import com.yeshua.forum.model.Topico;
import com.yeshua.forum.repository.ITopicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicoController {

    @Autowired
    private ITopicoRepository topicoRepo;

    @RequestMapping("/topicos")
    @ResponseBody
    public List<TopicoDto> topicos(String nomeCurso) {
        if (nomeCurso == null) {
            List<Topico> topicos = topicoRepo.findAll();
            return TopicoDto.converter(topicos);
        }
        List<Topico> topicos = topicoRepo.findByCursoNome(nomeCurso);
        return TopicoDto.converter(topicos);
    }
}
