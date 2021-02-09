package com.yeshua.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yeshua.forum.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nome);

}
