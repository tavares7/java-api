package com.yeshua.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.yeshua.forum.controller.dto.DetalhesDoTopicoDto;
import com.yeshua.forum.controller.dto.TopicoDto;
import com.yeshua.forum.controller.form.AtualizacaoTopicoForm;
import com.yeshua.forum.controller.form.TopicoForm;
import com.yeshua.forum.model.Topico;
import com.yeshua.forum.repository.ICursoRepository;
import com.yeshua.forum.repository.ITopicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/topicos")
public class TopicoController {

    @Autowired
    private ITopicoRepository topicoRepo;
    @Autowired
    private ICursoRepository cursoRepo;

    @GetMapping
    public Page<TopicoDto> listar(@RequestParam(required = false) String nomeCurso, @RequestParam int pagina,
            @RequestParam int qtd) {
        Pageable paginacao = PageRequest.of(pagina, qtd);
        if (nomeCurso == null) {
            Page<Topico> topicos = topicoRepo.findAll(paginacao);
            return TopicoDto.converter(topicos);
        }
        Page<Topico> topicos = topicoRepo.findByCursoNome(nomeCurso, paginacao);
        return TopicoDto.converter(topicos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder urlBuilder) {
        Topico topico = form.converter(cursoRepo);
        topicoRepo.save(topico);
        URI url = urlBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDoTopicoDto> detalher(@PathVariable Long id) {
        Optional<Topico> optional = topicoRepo.findById(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(new DetalhesDoTopicoDto(optional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form) {
        Optional<Topico> optional = topicoRepo.findById(id);
        if (optional.isPresent()) {
            Topico topico = form.atualizar(id, topicoRepo);
            return ResponseEntity.ok(new TopicoDto(topico));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        Optional<Topico> optional = topicoRepo.findById(id);
        if (optional.isPresent()) {
            topicoRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
