package br.eckelp.lancamentoconta.lancamento.useCase;

import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;
import br.eckelp.lancamentoconta.lancamento.dominio.dto.LancamentoCadastroForm;
import br.eckelp.lancamentoconta.lancamento.dominio.interfaces.ICadastrarLancamentoUseCase;
import br.eckelp.lancamentoconta.lancamento.dominio.interfaces.IValidadorLancamentoUseCase;
import br.eckelp.lancamentoconta.lancamento.infra.ILancamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastrarLancamentoUseCase extends AbstractSalvarLancamentoUseCase implements ICadastrarLancamentoUseCase {

    public CadastrarLancamentoUseCase(IValidadorLancamentoUseCase validadorLancamento, ILancamentoRepository repository) {
        super(validadorLancamento, repository);
    }

    @Override
    @Transactional
    public Lancamento cadastrar(LancamentoCadastroForm lancamentoCadastroForm) {

        Lancamento lancamento = lancamentoCadastroForm.criarLancamento();

        return this.salvar(lancamento);
    }


}
