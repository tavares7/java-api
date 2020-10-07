package com.yeshua.forum.repository;

import java.util.List;

import com.yeshua.forum.model.Topico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ITopicoRepository extends JpaRepository<Topico, Long> {

    List<Topico> findByCursoNome(String nomeCurso);

}
