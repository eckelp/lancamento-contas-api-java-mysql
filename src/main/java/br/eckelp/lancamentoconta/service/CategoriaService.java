package br.eckelp.lancamentoconta.service;

import br.eckelp.lancamentoconta.domain.exception.ObjetoNaoEncontradoException;
import br.eckelp.lancamentoconta.domain.model.Categoria;
import br.eckelp.lancamentoconta.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public Categoria buscarCategoriaPorId(Integer categoriaId) throws ObjetoNaoEncontradoException{

        if(categoriaId == null){
            throw new ObjetoNaoEncontradoException("Categoria não encontrada");
        }

        Optional<Categoria> categoriaOptional = this.repository.findById(categoriaId);

        return categoriaOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Categoria não encontrada"));
    }

}
