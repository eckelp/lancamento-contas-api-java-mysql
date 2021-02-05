package br.eckelp.lancamentoconta.categoria.dominio.dto;

import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaAtualizacaoForm {

    private Integer id;

    private String descricao;

    public Categoria criarCategoria() {
        return criarCategoria(id);
    }

    public Categoria criarCategoria(Integer categoriaId) {
        return Categoria.builder()
                .id(categoriaId)
                .descricao(descricao).build();
    }

    public CategoriaAtualizacaoForm(String descricao) {
        this.descricao = descricao;
    }
}
