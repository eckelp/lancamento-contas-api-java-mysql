package br.eckelp.lancamentoconta.formaPagamento.useCase;

import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formaPagamento.dominio.dto.FormaPagamentoAtualizacaoForm;
import br.eckelp.lancamentoconta.formaPagamento.dominio.interfaces.IAtualizarFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formaPagamento.dominio.interfaces.IBuscarUmaFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formaPagamento.dominio.interfaces.IValidadorFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formaPagamento.infra.IFormaPagamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AtualizarFormaPagamentoUseCase extends AbstractSalvarFormaPagamentoUseCase implements IAtualizarFormaPagamentoUseCase {

    private final IBuscarUmaFormaPagamentoUseCase buscarUmaFormaPagamentoUseCase;

    public AtualizarFormaPagamentoUseCase(IBuscarUmaFormaPagamentoUseCase buscarUmaFormaPagamentoUseCase, IValidadorFormaPagamentoUseCase validadorFormaPagamento, IFormaPagamentoRepository repository){
        super(validadorFormaPagamento, repository);
        this.buscarUmaFormaPagamentoUseCase = buscarUmaFormaPagamentoUseCase;
    }

    @Override
    @Transactional
    public FormaPagamento atualizar(Integer formaPagamentoId, FormaPagamentoAtualizacaoForm formaPagamentoAtualizacaoForm) {

        this.verificarSeFormaPagamentoExiste(formaPagamentoId);

        FormaPagamento formaPagamento = formaPagamentoAtualizacaoForm.criarFormaPagamento(formaPagamentoId);

        return this.salvar(formaPagamento);
    }

    private void verificarSeFormaPagamentoExiste(Integer formaPagamentoId) {
        this.buscarUmaFormaPagamentoUseCase.porId(formaPagamentoId);
    }

}
