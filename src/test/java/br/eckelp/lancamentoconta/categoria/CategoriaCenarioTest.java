package br.eckelp.lancamentoconta.categoria;

import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.app.security.repository.IUsuarioRepository;
import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.infra.ICategoriaRepository;
import org.springframework.context.annotation.Profile;

@Profile("test")
public class CategoriaCenarioTest {

    private final ICategoriaRepository repository;

    public CategoriaCenarioTest(ICategoriaRepository repository) {
        this.repository = repository;
        this.inicializarCenarioVazio();
    }

    public Categoria criarUmaCategoria(String descricao, Usuario usuario){
        Categoria categoria =  new Categoria(descricao, usuario);

        return repository.save(categoria);
    }

    public void criarListaCategoriasValidas(Usuario usuario){

        Categoria mercado = new Categoria("Mercado", usuario);
        Categoria alimentacao = new Categoria("Alimentação", usuario);

        repository.save(mercado);
        repository.save(alimentacao);

    }

    public void inicializarCenarioVazio(){
        repository.deleteAll();
    }


}
