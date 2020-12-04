package com.yeshua.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.yeshua.forum.model.Topico;
import com.yeshua.forum.repository.ITopicoRepository;

import org.hibernate.validator.constraints.Length;

public class AtualizacaoTopicoForm {
    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String titulo;
    @NotNull
    @NotEmpty
    @Length(min = 10)
    private String mensagem;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Topico atualizar(Long id, ITopicoRepository topicoRepo) {
        Topico topico = topicoRepo.getOne(id);
        topico.setTitulo(titulo);
        topico.setMensagem(mensagem);
        return topico;
    }

}
