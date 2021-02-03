package br.eckelp.lancamentoconta.resource.dto.categoria;

import br.eckelp.lancamentoconta.domain.model.Categoria;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class CategoriaDto {

    private Integer id;

    private String descricao;

    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.descricao = categoria.getDescricao();
    }

}
