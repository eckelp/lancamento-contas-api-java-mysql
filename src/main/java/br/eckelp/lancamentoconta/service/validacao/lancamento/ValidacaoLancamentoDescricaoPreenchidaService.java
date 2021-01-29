package br.eckelp.lancamentoconta.service.validacao.lancamento;

import br.eckelp.lancamentoconta.domain.exception.ArgumentoInvalidoException;
import br.eckelp.lancamentoconta.domain.model.Lancamento;
import org.springframework.stereotype.Service;

@Service
public class ValidacaoLancamentoDescricaoPreenchidaService implements ValidacaoLancamento {

    @Override
    public void validar(Lancamento lancamento) {

        String descricao = lancamento.getDescricao();

        if(descricao == null || descricao.isEmpty()){
            throw new ArgumentoInvalidoException("A descrição não pode ser vazia");
        }

    }
}
