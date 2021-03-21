package br.eckelp.lancamentoconta.categoria.usecase;

import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.dominio.interfaces.ISalvarCategoriaUseCase;
import br.eckelp.lancamentoconta.categoria.dominio.interfaces.IValidadorCategoriaUseCase;
import br.eckelp.lancamentoconta.categoria.infra.ICategoriaRepository;

public abstract class AbstractSalvarCategoriaUseCase implements ISalvarCategoriaUseCase {

    private final IValidadorCategoriaUseCase validadorCategoria;
    private final ICategoriaRepository repository;

    public AbstractSalvarCategoriaUseCase(IValidadorCategoriaUseCase validadorCategoria, ICategoriaRepository repository) {
        this.validadorCategoria = validadorCategoria;
        this.repository = repository;
    }

    @Override
    public final Categoria salvar(Categoria categoria){
        this.validar(categoria);
        return this.repository.save(categoria);
    }

    private void validar(Categoria categoria) {
        this.validadorCategoria.executar(categoria);
    }

}
