package br.eckelp.lancamentoconta.lancamento.usecase.validacao;

import br.eckelp.lancamentoconta.categoria.dominio.interfaces.IBuscarUmaCategoriaUseCase;
import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;
import br.eckelp.lancamentoconta.lancamento.dominio.interfaces.IValidacaoLancamentoUseCase;
import org.springframework.stereotype.Service;

@Service
public class ValidacaoLancamentoCategoriaExistenteUseCase implements IValidacaoLancamentoUseCase {

    private final IBuscarUmaCategoriaUseCase buscarCategoriaUseCase;

    public ValidacaoLancamentoCategoriaExistenteUseCase(IBuscarUmaCategoriaUseCase buscarCategoriaUseCase) {
        this.buscarCategoriaUseCase = buscarCategoriaUseCase;
    }

    @Override
    public void validar(Lancamento lancamento) {
        this.buscarCategoriaUseCase.porId(lancamento.getCategoriaId());
    }
}
