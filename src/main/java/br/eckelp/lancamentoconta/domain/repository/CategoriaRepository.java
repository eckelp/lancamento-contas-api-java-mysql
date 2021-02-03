package br.eckelp.lancamentoconta.domain.repository;

import br.eckelp.lancamentoconta.domain.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {


    @Query("SELECT c " +
            " FROM Categoria c")
    List<Categoria> listarCategorias();

    @Override
    @Modifying
    @Query("DELETE FROM Categoria c WHERE c.id = :categoriaId")
    void deleteById(Integer categoriaId);
}
