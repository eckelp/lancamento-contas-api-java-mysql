package br.eckelp.lancamentoconta.lancamento.useCase;

import br.eckelp.lancamentoconta.lancamento.dominio.interfaces.ISalvarLancamentoUseCase;
import br.eckelp.lancamentoconta.lancamento.dominio.interfaces.IValidadorLancamentoUseCase;
import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;
import br.eckelp.lancamentoconta.lancamento.infra.ILancamentoRepository;

public abstract class AbstractSalvarLancamentoUseCase implements ISalvarLancamentoUseCase {

    private final IValidadorLancamentoUseCase validadorLancamento;
    private final ILancamentoRepository repository;

    public AbstractSalvarLancamentoUseCase(IValidadorLancamentoUseCase validadorLancamento, ILancamentoRepository repository) {
        this.validadorLancamento = validadorLancamento;
        this.repository = repository;
    }

    @Override
    public final Lancamento salvar(Lancamento lancamento){
        this.validar(lancamento);

        return this.repository.saveAndFlush(lancamento);
    }

    private void validar(Lancamento lancamento){
        this.validadorLancamento.executar(lancamento);
    }

}
