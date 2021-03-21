package br.eckelp.lancamentoconta.formapagamento.usecase.validacao;

import br.eckelp.lancamentoconta.formapagamento.dominio.interfaces.IValidacaoFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.app.exception.ArgumentoInvalidoException;
import br.eckelp.lancamentoconta.formapagamento.dominio.FormaPagamento;
import org.springframework.stereotype.Service;

@Service
public class ValidacaoFormaPagamentoDescricaoPreenchidaService implements IValidacaoFormaPagamentoUseCase {


    @Override
    public void validar(FormaPagamento formaPagamento) {

        String descricao = formaPagamento.getDescricao();

        if(descricao == null || descricao.isEmpty()){
            throw new ArgumentoInvalidoException("A descrição não pode ser vazia");
        }
    }
}
