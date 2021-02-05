package br.eckelp.lancamentoconta.categoria.useCase;

import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.dominio.interfaces.IBuscarTodasCategoriasUseCase;
import br.eckelp.lancamentoconta.categoria.infra.ICategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarTodasCategoriasUseCase implements IBuscarTodasCategoriasUseCase {

    private final ICategoriaRepository repository;

    public BuscarTodasCategoriasUseCase(ICategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Categoria> buscarTodas() {

        List<Categoria> listaCategorias = this.repository.listarCategorias();

        return listaCategorias;
    }
}
