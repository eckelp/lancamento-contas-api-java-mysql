package br.eckelp.lancamentoconta.categoria.dominio.interfaces;

import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.dominio.dto.CategoriaAtualizacaoForm;

public interface IAtualizarCategoriaUseCase {

    Categoria atualizar(Integer categoriaId, CategoriaAtualizacaoForm form);

}
