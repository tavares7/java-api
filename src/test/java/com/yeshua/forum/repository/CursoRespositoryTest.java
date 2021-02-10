package com.yeshua.forum.repository;

import com.yeshua.forum.modelo.Curso;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CursoRespositoryTest {

    @Autowired
    private CursoRepository repository;

    @Test
    public void deveriaCarregaCursoAoBuscarNome() {
        String nome = "HTML 5";
        Curso curso = repository.findByNome(nome);
        Assert.assertNotNull(curso);
        Assert.assertEquals(nome, curso.getNome());
    }

    @Test
    public void naoDeveriaCarregaCurso() {
        String nome = "JPA";
        Curso curso = repository.findByNome(nome);
        Assert.assertNull(curso);
    }
}
