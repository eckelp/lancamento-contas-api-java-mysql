package br.eckelp.lancamentoconta.categoria.usecase;

import br.eckelp.lancamentoconta.app.security.context.UsuarioContext;
import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.dominio.interfaces.IAtualizarCategoriaUseCase;
import br.eckelp.lancamentoconta.categoria.dominio.interfaces.IBuscarUmaCategoriaUseCase;
import br.eckelp.lancamentoconta.categoria.dominio.interfaces.IValidadorCategoriaUseCase;
import br.eckelp.lancamentoconta.categoria.dominio.dto.CategoriaAtualizacaoForm;
import br.eckelp.lancamentoconta.categoria.infra.ICategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AtualizarCategoriaUseCase extends AbstractSalvarCategoriaUseCase implements IAtualizarCategoriaUseCase {

    private final IBuscarUmaCategoriaUseCase buscarCategoriaUseCase;

    public AtualizarCategoriaUseCase(IBuscarUmaCategoriaUseCase buscarCategoriaUseCase, IValidadorCategoriaUseCase validadorCategoria, ICategoriaRepository repository) {
        super(validadorCategoria, repository);
        this.buscarCategoriaUseCase = buscarCategoriaUseCase;
    }

    @Override
    @Transactional
    public Categoria atualizar(Integer categoriaId, CategoriaAtualizacaoForm categoriaAtualizacaoForm) {

        this.verificarSeCategoriaExiste(categoriaId);

        Usuario usuario = UsuarioContext.getUsuario();

        Categoria categoria = categoriaAtualizacaoForm.criarCategoria(categoriaId, usuario);

        categoria = this.salvar(categoria);

        return categoria;
    }

    private void verificarSeCategoriaExiste(Integer categoriaId){
        this.buscarCategoriaUseCase.porId(categoriaId);
    }

}
