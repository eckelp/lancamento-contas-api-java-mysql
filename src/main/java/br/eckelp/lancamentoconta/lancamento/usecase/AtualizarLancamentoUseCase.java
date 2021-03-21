package br.eckelp.lancamentoconta.lancamento.usecase;

import br.eckelp.lancamentoconta.app.security.context.UsuarioContext;
import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.lancamento.dominio.interfaces.IAtualizarLancamentoUseCase;
import br.eckelp.lancamentoconta.lancamento.dominio.interfaces.IBuscarUmLancamentoUseCase;
import br.eckelp.lancamentoconta.lancamento.dominio.interfaces.IValidadorLancamentoUseCase;
import br.eckelp.lancamentoconta.lancamento.dominio.dto.LancamentoAtualizacaoForm;
import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;
import br.eckelp.lancamentoconta.lancamento.infra.ILancamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AtualizarLancamentoUseCase extends AbstractSalvarLancamentoUseCase implements IAtualizarLancamentoUseCase {

    private final IBuscarUmLancamentoUseCase buscarUmLancamentoUseCase;

    public AtualizarLancamentoUseCase(IBuscarUmLancamentoUseCase buscarUmLancamentoUseCase, IValidadorLancamentoUseCase validadorLancamento, ILancamentoRepository repository) {
        super(validadorLancamento, repository);
        this.buscarUmLancamentoUseCase = buscarUmLancamentoUseCase;
    }

    @Override
    @Transactional
    public Lancamento atualizar(Integer lancamentoId, LancamentoAtualizacaoForm lancamentoAtualizacaoForm) {
        this.verificarSeLancamentoExiste(lancamentoId);

        Usuario usuario = UsuarioContext.getUsuario();

        Lancamento lancamento = lancamentoAtualizacaoForm.criarLancamento(lancamentoId, usuario);

        return this.salvar(lancamento);
    }

    private void verificarSeLancamentoExiste(Integer lancamentoId) {
        this.buscarUmLancamentoUseCase.porId(lancamentoId);
    }

}
