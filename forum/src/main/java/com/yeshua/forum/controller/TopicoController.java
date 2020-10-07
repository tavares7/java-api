package com.yeshua.forum.controller;

import java.util.List;

import com.yeshua.forum.controller.dto.TopicoDto;
import com.yeshua.forum.controller.form.TopicoForm;
import com.yeshua.forum.model.Topico;
import com.yeshua.forum.repository.ICursoRepository;
import com.yeshua.forum.repository.ITopicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/topicos")
public class TopicoController {

    @Autowired
    private ITopicoRepository topicoRepo;
    @Autowired
    private ICursoRepository cursoRepo;

    @GetMapping
    public List<TopicoDto> topicos(String nomeCurso) {
        if (nomeCurso == null) {
            List<Topico> topicos = topicoRepo.findAll();
            return TopicoDto.converter(topicos);
        }
        List<Topico> topicos = topicoRepo.findByCursoNome(nomeCurso);
        return TopicoDto.converter(topicos);
    }

    @PostMapping
    public void cadastrar(@RequestBody TopicoForm form) {
        Topico topico = form.converter(cursoRepo);
        topicoRepo.save(topico);
    }
}
