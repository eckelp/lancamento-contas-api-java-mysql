package br.eckelp.lancamentoconta.domain.service;

import br.eckelp.lancamentoconta.domain.exception.ObjetoNaoEncontradoException;
import br.eckelp.lancamentoconta.domain.model.Categoria;
import br.eckelp.lancamentoconta.domain.repository.CategoriaRepository;
import br.eckelp.lancamentoconta.domain.service.validacao.categoria.ValidacaoCategoria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;
    private final List<ValidacaoCategoria> validacoes;

    public CategoriaService(CategoriaRepository repository, List<ValidacaoCategoria> validacoes) {
        this.repository = repository;
        this.validacoes = validacoes;
    }

    public Categoria buscarCategoriaPorId(Integer categoriaId) throws ObjetoNaoEncontradoException {

        if (categoriaId == null) {
            throw new ObjetoNaoEncontradoException("Categoria não encontrada");
        }

        Optional<Categoria> categoriaOptional = this.repository.findById(categoriaId);

        return categoriaOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Categoria não encontrada"));
    }

    @Transactional
    public Categoria criarCategoria(Categoria categoria) {

        categoria = this.salvarCategoria(categoria);

        return categoria;
    }

    @Transactional
    public Categoria atualizarCategoria(Integer categoriaId, Categoria categoria) {

        this.verificarSeCategoriaExiste(categoriaId);

        categoria = this.salvarCategoria(categoria);

        return categoria;
    }

    public void removerCategoria(Integer categoriaId) {
        this.repository.deleteById(categoriaId);
    }

    public List<Categoria> listarCategorias() {

        List<Categoria> listaCategorias = this.repository.listarCategorias();

        return listaCategorias;
    }

    private void verificarSeCategoriaExiste(Integer categoriaId){
        this.buscarCategoriaPorId(categoriaId);
    }

    private Categoria salvarCategoria(Categoria categoria) {
        this.valicarCategoria(categoria);

        return this.repository.save(categoria);
    }

    private void valicarCategoria(Categoria categoria) {
        this.validacoes.forEach(validacao -> validacao.validar(categoria));
    }

}
