package br.eckelp.lancamentoconta.resource;

import br.eckelp.lancamentoconta.domain.dto.categoria.CategoriaAtualizacaoDTO;
import br.eckelp.lancamentoconta.domain.dto.categoria.CategoriaCadastroDTO;
import br.eckelp.lancamentoconta.domain.dto.categoria.CategoriaListagemDTO;
import br.eckelp.lancamentoconta.service.CategoriaService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    private final CategoriaService categoriaService;

    public CategoriaResource(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<?> criarCategoria(@RequestBody CategoriaCadastroDTO categoriaCadastroDTO ){
        categoriaCadastroDTO = this.categoriaService.criarCategoria(categoriaCadastroDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCadastroDTO);
    }

    @PutMapping("/{categoriaId}")
    public ResponseEntity<?> atualizarCategoria(@PathVariable Integer categoriaId, @RequestBody CategoriaAtualizacaoDTO categoriaAtualizacaoDTO){
        categoriaAtualizacaoDTO = this.categoriaService.atualizarCategoria(categoriaId, categoriaAtualizacaoDTO);

        return ResponseEntity.ok(categoriaAtualizacaoDTO);
    }

    @DeleteMapping("/{categoriaId}")
    public ResponseEntity<?> removerCategoria(@PathVariable Integer categoriaId) {

        try {
            this.categoriaService.removerCategoria(categoriaId);
        } catch (EmptyResultDataAccessException e) {
            //Se não encontrar o registro então a função do endpoint também foi realizada: Não ter mais o recurso informado na base
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CategoriaListagemDTO>> listarCategorias() {
        List<CategoriaListagemDTO> listaCategorias = this.categoriaService.listarCategorias();

        return ResponseEntity.ok(listaCategorias);
    }
}
