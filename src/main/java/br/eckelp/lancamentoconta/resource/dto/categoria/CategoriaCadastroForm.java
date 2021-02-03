package br.eckelp.lancamentoconta.resource.dto.categoria;

import br.eckelp.lancamentoconta.domain.model.Categoria;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CategoriaCadastroForm {

    private Integer id;

    private String descricao;

    public Categoria converter() {
        Categoria categoria = Categoria.builder()
                .id(id)
                .descricao(descricao)
                .build();

        return categoria;
    }
}
