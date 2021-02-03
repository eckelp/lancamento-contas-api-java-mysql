package br.eckelp.lancamentoconta.resource.dto.categoria;

import br.eckelp.lancamentoconta.domain.model.Categoria;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CategoriaAtualizacaoForm {

    private Integer id;

    private String descricao;

    public Categoria converter() {
        return converter(id);
    }

    public Categoria converter(Integer categoriaId) {
        return Categoria.builder()
                .id(categoriaId)
                .descricao(descricao).build();
    }
}
