package br.eckelp.lancamentoconta.categoria.usecase;

import br.eckelp.lancamentoconta.categoria.dominio.interfaces.IRemoverCategoriaUseCase;
import br.eckelp.lancamentoconta.categoria.infra.ICategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class RemoverCategoriaUseCase implements IRemoverCategoriaUseCase {

    private final ICategoriaRepository repository;

    public RemoverCategoriaUseCase(ICategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void remover(Integer categoriaId) {
        this.repository.deleteById(categoriaId);
    }
}
