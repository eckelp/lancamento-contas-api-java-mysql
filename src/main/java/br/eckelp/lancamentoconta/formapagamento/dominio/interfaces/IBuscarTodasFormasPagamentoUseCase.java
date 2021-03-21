package br.eckelp.lancamentoconta.formapagamento.dominio.interfaces;

import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.formapagamento.dominio.FormaPagamento;

import java.util.List;

public interface IBuscarTodasFormasPagamentoUseCase {

    List<FormaPagamento> buscarTodas(Usuario usuario);

}
