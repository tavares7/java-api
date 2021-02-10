package com.yeshua.forum.repository;

import com.yeshua.forum.modelo.Curso;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CursoRespositoryTest {

    @Autowired
    private CursoRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    public void deveriaCarregaCursoAoBuscarNome() {
        String cursoNome = "HTML 5";

        Curso cursoHTML5 = new Curso();
        cursoHTML5.setNome(cursoNome);
        cursoHTML5.setCategoria("Programação");
        em.persist(cursoHTML5);

        Curso curso = repository.findByNome(cursoNome);
        Assert.assertNotNull(curso);
        Assert.assertEquals(cursoNome, curso.getNome());
    }

    @Test
    public void naoDeveriaCarregaCurso() {
        String nome = "JPA";
        Curso curso = repository.findByNome(nome);
        Assert.assertNull(curso);
    }
}
