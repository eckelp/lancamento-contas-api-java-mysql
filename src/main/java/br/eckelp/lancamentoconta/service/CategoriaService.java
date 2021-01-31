package br.eckelp.lancamentoconta.service;

import br.eckelp.lancamentoconta.domain.dto.categoria.CategoriaAtualizacaoDTO;
import br.eckelp.lancamentoconta.domain.dto.categoria.CategoriaCadastroDTO;
import br.eckelp.lancamentoconta.domain.dto.categoria.CategoriaListagemDTO;
import br.eckelp.lancamentoconta.domain.dto.categoria.ICategoriaDTO;
import br.eckelp.lancamentoconta.domain.exception.ObjetoNaoEncontradoException;
import br.eckelp.lancamentoconta.domain.model.Categoria;
import br.eckelp.lancamentoconta.repository.CategoriaRepository;
import br.eckelp.lancamentoconta.service.validacao.categoria.ValidacaoCategoria;
import br.eckelp.lancamentoconta.service.validacao.categoria.ValidacaoCategoriaDescricaoPreenchidaService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;
    private final List<ValidacaoCategoria> validacoes;

    public CategoriaService(CategoriaRepository repository, ValidacaoCategoriaDescricaoPreenchidaService validacaoCategoriaDescricaoPreenchidaService) {
        this.repository = repository;

        this.validacoes = Arrays.asList(validacaoCategoriaDescricaoPreenchidaService);
    }

    public Categoria buscarCategoriaPorId(Integer categoriaId) throws ObjetoNaoEncontradoException {

        if (categoriaId == null) {
            throw new ObjetoNaoEncontradoException("Categoria não encontrada");
        }

        Optional<Categoria> categoriaOptional = this.repository.findById(categoriaId);

        return categoriaOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Categoria não encontrada"));
    }

    public CategoriaCadastroDTO criarCategoria(CategoriaCadastroDTO categoriaCadastroDTO) {
        Categoria categoria = this.getCategoria(categoriaCadastroDTO);

        Categoria novaCategoria = this.salvarCategoria(categoria);

        categoriaCadastroDTO.setId(novaCategoria.getId());

        return categoriaCadastroDTO;
    }

    public CategoriaAtualizacaoDTO atualizarCategoria(Integer categoriaId, CategoriaAtualizacaoDTO categoriaAtualizacaoDTO) {

        this.verificarSeCategoriaExiste(categoriaId);

        Categoria categoria = this.getCategoria(categoriaId, categoriaAtualizacaoDTO);

        categoria = this.salvarCategoria(categoria);

        categoriaAtualizacaoDTO.setId(categoria.getId());

        return categoriaAtualizacaoDTO;
    }

    public void removerCategoria(Integer categoriaId) {
        this.repository.deleteById(categoriaId);
    }

    public List<CategoriaListagemDTO> listarCategorias() {

        List<CategoriaListagemDTO> listaCategorias = this.repository.listarCategorias();

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

    private Categoria getCategoria(Integer categoiaId, ICategoriaDTO iCategoria) {
        Categoria categoria = Categoria.builder()
                .id(categoiaId)
                .descricao(iCategoria.getDescricao())
                .build();

        return categoria;
    }

    private Categoria getCategoria(ICategoriaDTO iCategoria) {
        return getCategoria(null, iCategoria);
    }

}
