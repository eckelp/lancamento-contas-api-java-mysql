package br.eckelp.lancamentoconta.resource;

import br.eckelp.lancamentoconta.domain.model.Categoria;
import br.eckelp.lancamentoconta.resource.dto.categoria.CategoriaAtualizacaoForm;
import br.eckelp.lancamentoconta.resource.dto.categoria.CategoriaCadastroForm;
import br.eckelp.lancamentoconta.resource.dto.categoria.CategoriaDto;
import br.eckelp.lancamentoconta.domain.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

    private final CategoriaService categoriaService;

    public CategoriaResource(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> criarCategoria(@RequestBody CategoriaCadastroForm categoriaCadastroForm){
        Categoria categoria = categoriaCadastroForm.converter();

        categoria = this.categoriaService.criarCategoria(categoria);

        return ResponseEntity.status(HttpStatus.CREATED).body(new CategoriaDto(categoria));
    }

    @PutMapping("/{categoriaId}")
    public ResponseEntity<CategoriaDto> atualizarCategoria(@PathVariable Integer categoriaId, @RequestBody CategoriaAtualizacaoForm categoriaAtualizacaoForm){

        Categoria categoria = categoriaAtualizacaoForm.converter(categoriaId);

        categoria = this.categoriaService.atualizarCategoria(categoriaId, categoria);

        return ResponseEntity.ok(new CategoriaDto(categoria));
    }

    @DeleteMapping("/{categoriaId}")
    public ResponseEntity<?> removerCategoria(@PathVariable Integer categoriaId) {

        this.categoriaService.removerCategoria(categoriaId);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> listarCategorias() {
        List<Categoria> listaCategorias = this.categoriaService.listarCategorias();

        List<CategoriaDto> lista = listaCategorias.stream().map(categoria -> new CategoriaDto(categoria)).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }
}
