package com.yeshua.forum.repository;

import java.util.Optional;

import com.yeshua.forum.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
