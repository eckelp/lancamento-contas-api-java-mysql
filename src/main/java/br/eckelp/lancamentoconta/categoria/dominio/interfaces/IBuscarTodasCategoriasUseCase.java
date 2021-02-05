package br.eckelp.lancamentoconta.categoria.dominio.interfaces;

import br.eckelp.lancamentoconta.categoria.dominio.Categoria;

import java.util.List;

public interface IBuscarTodasCategoriasUseCase {

    List<Categoria> buscarTodas();

}
