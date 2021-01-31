package br.eckelp.lancamentoconta.repository;

import br.eckelp.lancamentoconta.domain.dto.categoria.CategoriaListagemDTO;
import br.eckelp.lancamentoconta.domain.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {


    @Query("SELECT new br.eckelp.lancamentoconta.domain.dto.categoria.CategoriaListagemDTO(" +
            "      c.id " +
            "     ,c.descricao " +
            ")" +
            " FROM Categoria c")
    List<CategoriaListagemDTO> listarCategorias();
}
