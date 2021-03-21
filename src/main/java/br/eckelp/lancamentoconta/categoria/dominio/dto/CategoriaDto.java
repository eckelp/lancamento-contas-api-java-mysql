package br.eckelp.lancamentoconta.categoria.dominio.dto;

import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDto {

    private Integer id;

    private String descricao;

    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.descricao = categoria.getDescricao();
    }

}
