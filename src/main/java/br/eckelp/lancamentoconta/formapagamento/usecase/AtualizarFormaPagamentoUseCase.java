package br.eckelp.lancamentoconta.formapagamento.usecase;

import br.eckelp.lancamentoconta.app.security.context.UsuarioContext;
import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.formapagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formapagamento.dominio.dto.FormaPagamentoAtualizacaoForm;
import br.eckelp.lancamentoconta.formapagamento.dominio.interfaces.IAtualizarFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formapagamento.dominio.interfaces.IBuscarUmaFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formapagamento.dominio.interfaces.IValidadorFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formapagamento.infra.IFormaPagamentoRepository;
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

        Usuario usuario = UsuarioContext.getUsuario();

        FormaPagamento formaPagamento = formaPagamentoAtualizacaoForm.criarFormaPagamento(formaPagamentoId, usuario);

        return this.salvar(formaPagamento);
    }

    private void verificarSeFormaPagamentoExiste(Integer formaPagamentoId) {
        this.buscarUmaFormaPagamentoUseCase.porId(formaPagamentoId);
    }

}
