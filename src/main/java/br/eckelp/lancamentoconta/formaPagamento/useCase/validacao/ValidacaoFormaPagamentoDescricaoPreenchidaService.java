package br.eckelp.lancamentoconta.formaPagamento.useCase.validacao;

import br.eckelp.lancamentoconta.formaPagamento.dominio.interfaces.IValidacaoFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.sistema.infra.exception.ArgumentoInvalidoException;
import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
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
