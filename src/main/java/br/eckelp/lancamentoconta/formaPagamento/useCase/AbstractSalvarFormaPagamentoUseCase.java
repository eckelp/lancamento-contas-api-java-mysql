package br.eckelp.lancamentoconta.formaPagamento.useCase;

import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formaPagamento.dominio.interfaces.ISalvarFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formaPagamento.dominio.interfaces.IValidadorFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formaPagamento.infra.IFormaPagamentoRepository;

public abstract class AbstractSalvarFormaPagamentoUseCase implements ISalvarFormaPagamentoUseCase {

    private final IValidadorFormaPagamentoUseCase validadorFormaPagamento;
    private final IFormaPagamentoRepository repository;

    public AbstractSalvarFormaPagamentoUseCase(IValidadorFormaPagamentoUseCase validadorFormaPagamento, IFormaPagamentoRepository repository) {
        this.validadorFormaPagamento = validadorFormaPagamento;
        this.repository = repository;
    }

    public final FormaPagamento salvar(FormaPagamento formaPagamento){
        this.validadorFormaPagamento.executar(formaPagamento);

        return this.repository.save(formaPagamento);
    }
}
