package br.eckelp.lancamentoconta.categoria.usecase;

import br.eckelp.lancamentoconta.app.security.context.UsuarioContext;
import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.dominio.interfaces.ICadastrarCategoriaUseCase;
import br.eckelp.lancamentoconta.categoria.dominio.interfaces.IValidadorCategoriaUseCase;
import br.eckelp.lancamentoconta.categoria.dominio.dto.CategoriaCadastroForm;
import br.eckelp.lancamentoconta.categoria.infra.ICategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastrarCategoriaUseCase extends AbstractSalvarCategoriaUseCase implements ICadastrarCategoriaUseCase {

    public CadastrarCategoriaUseCase(IValidadorCategoriaUseCase validadorCategoriaUseCase, ICategoriaRepository repository) {
        super(validadorCategoriaUseCase, repository);
    }

    @Override
    @Transactional
    public Categoria cadastrar(CategoriaCadastroForm categoriaCadastroForm){
        Usuario usuario = UsuarioContext.getUsuario();

        Categoria categoria = categoriaCadastroForm.criarCategoria(usuario);

        return this.salvar(categoria);

    }

}
