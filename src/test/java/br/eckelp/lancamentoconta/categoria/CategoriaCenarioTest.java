package br.eckelp.lancamentoconta.categoria;

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

    public Categoria criarUmaCategoria(String descricao){
        Categoria categoria =  new Categoria(descricao);

        return repository.save(categoria);
    }

    public void criarListaCategoriasValidas(){

        Categoria mercado = new Categoria("Mercado");
        Categoria alimentacao = new Categoria("Alimentação");

        mercado = repository.save(mercado);
        alimentacao = repository.save(alimentacao);

    }

    public void inicializarCenarioVazio(){
        repository.deleteAll();
    }



}
