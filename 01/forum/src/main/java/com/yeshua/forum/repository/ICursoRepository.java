package com.yeshua.forum.repository;

import com.yeshua.forum.model.Curso;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICursoRepository extends JpaRepository<Curso, Long> {

    Curso findByNome(String nome);

}
