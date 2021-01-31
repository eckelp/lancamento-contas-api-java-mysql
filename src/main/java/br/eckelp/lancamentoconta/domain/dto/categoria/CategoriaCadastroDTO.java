package br.eckelp.lancamentoconta.domain.dto.categoria;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaCadastroDTO implements ICategoriaDTO{

    private Integer id;

    private String descricao;

}
