package br.eckelp.lancamentoconta.categoria.dominio.interfaces;

import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.app.exception.ObjetoNaoEncontradoException;

public interface IBuscarUmaCategoriaUseCase {

    Categoria porId(Integer id) throws ObjetoNaoEncontradoException;

}
