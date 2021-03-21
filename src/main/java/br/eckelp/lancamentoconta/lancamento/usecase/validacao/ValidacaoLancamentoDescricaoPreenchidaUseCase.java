package br.eckelp.lancamentoconta.lancamento.usecase.validacao;

import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;
import br.eckelp.lancamentoconta.lancamento.dominio.interfaces.IValidacaoLancamentoUseCase;
import br.eckelp.lancamentoconta.app.exception.ArgumentoInvalidoException;
import org.springframework.stereotype.Service;

@Service
public class ValidacaoLancamentoDescricaoPreenchidaUseCase implements IValidacaoLancamentoUseCase {

    @Override
    public void validar(Lancamento lancamento) {

        String descricao = lancamento.getDescricao();

        if(descricao == null || descricao.isEmpty()){
            throw new ArgumentoInvalidoException("A descrição não pode ser vazia");
        }

    }
}
