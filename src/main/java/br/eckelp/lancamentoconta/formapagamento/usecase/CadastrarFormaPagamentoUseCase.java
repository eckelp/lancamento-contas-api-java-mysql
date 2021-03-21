package br.eckelp.lancamentoconta.formapagamento.usecase;

import br.eckelp.lancamentoconta.app.security.context.UsuarioContext;
import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.formapagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formapagamento.dominio.dto.FormaPagamentoCadastroForm;
import br.eckelp.lancamentoconta.formapagamento.dominio.interfaces.ICadastrarFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formapagamento.dominio.interfaces.IValidadorFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formapagamento.infra.IFormaPagamentoRepository;
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
        Usuario usuario = UsuarioContext.getUsuario();

        FormaPagamento formaPagamento = formaPagamentoCadastroForm.criarFormaPagamento(usuario);

        return this.salvar(formaPagamento);
    }

}
