package br.eckelp.lancamentoconta.categoria.controller;

import br.eckelp.lancamentoconta.app.security.context.UsuarioContext;
import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.categoria.dominio.*;
import br.eckelp.lancamentoconta.categoria.dominio.dto.CategoriaAtualizacaoForm;
import br.eckelp.lancamentoconta.categoria.dominio.dto.CategoriaCadastroForm;
import br.eckelp.lancamentoconta.categoria.dominio.dto.CategoriaDto;
import br.eckelp.lancamentoconta.categoria.dominio.interfaces.IAtualizarCategoriaUseCase;
import br.eckelp.lancamentoconta.categoria.dominio.interfaces.IBuscarTodasCategoriasUseCase;
import br.eckelp.lancamentoconta.categoria.dominio.interfaces.ICadastrarCategoriaUseCase;
import br.eckelp.lancamentoconta.categoria.dominio.interfaces.IRemoverCategoriaUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final IBuscarTodasCategoriasUseCase buscarTodasCategoriasUseCase;
    private final ICadastrarCategoriaUseCase cadastrarCategoriaUseCase;
    private final IAtualizarCategoriaUseCase atualizarCategoriaUseCase;
    private final IRemoverCategoriaUseCase removerCategoriaUseCase;

    public CategoriaController(IBuscarTodasCategoriasUseCase buscarTodasCategoriasUseCase,
                               ICadastrarCategoriaUseCase cadastrarCategoriaUseCase,
                               IAtualizarCategoriaUseCase atualizarCategoriaUseCase,
                               IRemoverCategoriaUseCase removerCategoriaUseCase) {
        this.buscarTodasCategoriasUseCase = buscarTodasCategoriasUseCase;
        this.cadastrarCategoriaUseCase = cadastrarCategoriaUseCase;
        this.atualizarCategoriaUseCase = atualizarCategoriaUseCase;
        this.removerCategoriaUseCase = removerCategoriaUseCase;
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> criarCategoria(@RequestBody CategoriaCadastroForm categoriaCadastroForm){

        Categoria categoria = this.cadastrarCategoriaUseCase.cadastrar(categoriaCadastroForm);

        return ResponseEntity.status(HttpStatus.CREATED).body(new CategoriaDto(categoria));
    }

    @PutMapping("/{categoriaId}")
    public ResponseEntity<CategoriaDto> atualizarCategoria(@PathVariable Integer categoriaId, @RequestBody CategoriaAtualizacaoForm categoriaAtualizacaoForm){

        Categoria categoria = this.atualizarCategoriaUseCase.atualizar(categoriaId, categoriaAtualizacaoForm);

        return ResponseEntity.ok(new CategoriaDto(categoria));
    }

    @DeleteMapping("/{categoriaId}")
    public ResponseEntity<?> removerCategoria(@PathVariable Integer categoriaId) {

        this.removerCategoriaUseCase.remover(categoriaId);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> listarCategorias() {
        Usuario usuario = UsuarioContext.getUsuario();

        List<Categoria> listaCategorias = this.buscarTodasCategoriasUseCase.buscarTodas(usuario);

        List<CategoriaDto> lista = listaCategorias.stream().map(CategoriaDto::new).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }
}
