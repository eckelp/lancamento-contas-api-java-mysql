package br.eckelp.lancamentoconta.app.security.repository;

import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String login);

    @Query("SELECT COUNT(u.id) FROM Usuario u where email = :email")
    Integer buscarTotalDeUsuariosPorEmail(String email);
}
