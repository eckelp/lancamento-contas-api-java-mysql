package br.eckelp.lancamentoconta.categoria.dominio.interfaces;

import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.sistema.infra.exception.ObjetoNaoEncontradoException;

public interface IBuscarUmaCategoriaUseCase {

    Categoria porId(Integer id) throws ObjetoNaoEncontradoException;

}
