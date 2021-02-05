package br.eckelp.lancamentoconta.formaPagamento.useCase;

import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formaPagamento.dominio.dto.FormaPagamentoCadastroForm;
import br.eckelp.lancamentoconta.formaPagamento.dominio.interfaces.ICadastrarFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formaPagamento.dominio.interfaces.IValidadorFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formaPagamento.infra.IFormaPagamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastrarFormaPagamentoUseCase extends AbstractSalvarFormaPagamentoUseCase implements ICadastrarFormaPagamentoUseCase {

    public CadastrarFormaPagamentoUseCase(IValidadorFormaPagamentoUseCase validadorFormaPagamento, IFormaPagamentoRepository repository) {
        super(validadorFormaPagamento, repository);
    }

    @Override
    @Transactional
    public FormaPagamento cadastrar(FormaPagamentoCadastroForm formaPagamentoCadastroForm) {

        FormaPagamento formaPagamento = formaPagamentoCadastroForm.criarFormaPagamento();

        return this.salvar(formaPagamento);
    }

}
