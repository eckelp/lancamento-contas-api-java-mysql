package br.eckelp.lancamentoconta.categoria.dominio.interfaces;

import br.eckelp.lancamentoconta.categoria.dominio.Categoria;

public interface IValidacaoCategoriaUseCase {

    void validar(Categoria categoria);

}
