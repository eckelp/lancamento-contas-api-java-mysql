package br.eckelp.lancamentoconta.service.validacao.formaPagamento;

import br.eckelp.lancamentoconta.domain.exception.ArgumentoInvalidoException;
import br.eckelp.lancamentoconta.domain.model.FormaPagamento;
import org.springframework.stereotype.Service;

@Service
public class ValidacaoFormaPagamentoDescricaoPreenchidaService implements ValidacaoFormaPagamento {


    @Override
    public void validar(FormaPagamento formaPagamento) {

        String descricao = formaPagamento.getDescricao();

        if(descricao == null || descricao.isEmpty()){
            throw new ArgumentoInvalidoException("A descrição não pode ser vazia");
        }
    }
}
