package br.eckelp.lancamentoconta.categoria.dominio.dto;

import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import lombok.Data;

@Data
public class CategoriaDto {

    private Integer id;

    private String descricao;

    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.descricao = categoria.getDescricao();
    }

}
