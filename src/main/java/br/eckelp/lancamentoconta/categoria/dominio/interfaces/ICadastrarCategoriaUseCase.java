package br.eckelp.lancamentoconta.categoria.dominio.interfaces;

import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.dominio.dto.CategoriaCadastroForm;

public interface ICadastrarCategoriaUseCase {

    Categoria cadastrar(CategoriaCadastroForm form);
}
