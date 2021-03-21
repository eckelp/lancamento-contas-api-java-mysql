package br.eckelp.lancamentoconta.categoria.usecase;

import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.dominio.interfaces.IBuscarUmaCategoriaUseCase;
import br.eckelp.lancamentoconta.categoria.infra.ICategoriaRepository;
import br.eckelp.lancamentoconta.app.exception.ObjetoNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarUmaCategoriaUseCase implements IBuscarUmaCategoriaUseCase {

    private final ICategoriaRepository repository;

    public BuscarUmaCategoriaUseCase(ICategoriaRepository repository) {
        this.repository = repository;
    }


    @Override
    public Categoria porId(Integer categoriaId) throws ObjetoNaoEncontradoException {

        if (categoriaId == null) {
            throw new ObjetoNaoEncontradoException("Categoria não encontrada");
        }

        Optional<Categoria> categoriaOptional = this.repository.findById(categoriaId);

        return categoriaOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Categoria não encontrada"));
    }

}
