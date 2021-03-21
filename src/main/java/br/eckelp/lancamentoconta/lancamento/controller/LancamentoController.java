package br.eckelp.lancamentoconta.lancamento.controller;

import br.eckelp.lancamentoconta.app.security.context.UsuarioContext;
import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;
import br.eckelp.lancamentoconta.lancamento.dominio.dto.LancamentoAtualizacaoForm;
import br.eckelp.lancamentoconta.lancamento.dominio.dto.LancamentoCadastroForm;
import br.eckelp.lancamentoconta.lancamento.dominio.dto.LancamentoDto;
import br.eckelp.lancamentoconta.lancamento.dominio.interfaces.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    private final ICadastrarLancamentoUseCase cadastrarLancamentoUseCase;
    private final IAtualizarLancamentoUseCase atualizarLancamentoUseCase;
    private final IBuscarTodosLancamentosPorDataUseCase buscarTodosLancamentoPorDataUseCase;
    private final IBuscarTodosLancamentosUseCase buscarTodosLancamentosUseCase;
    private final IRemoverLancamentoUseCase removerLancamentoUseCase;

    public LancamentoController(ICadastrarLancamentoUseCase cadastrarLancamentoUseCase,
                                IAtualizarLancamentoUseCase atualizarLancamentoUseCase,
                                IBuscarTodosLancamentosPorDataUseCase buscarTodosLancamentoPorDataUseCase,
                                IBuscarTodosLancamentosUseCase buscarTodosLancamentosUseCase,
                                IRemoverLancamentoUseCase removerLancamentoUseCase) {
        this.cadastrarLancamentoUseCase = cadastrarLancamentoUseCase;
        this.atualizarLancamentoUseCase = atualizarLancamentoUseCase;
        this.buscarTodosLancamentoPorDataUseCase = buscarTodosLancamentoPorDataUseCase;
        this.buscarTodosLancamentosUseCase = buscarTodosLancamentosUseCase;
        this.removerLancamentoUseCase = removerLancamentoUseCase;
    }

    @PostMapping
    public ResponseEntity<LancamentoDto> criarLancamento(@RequestBody LancamentoCadastroForm lancamentoCadastroForm){

        Lancamento lancamento = this.cadastrarLancamentoUseCase.cadastrar(lancamentoCadastroForm);

        return ResponseEntity.status(HttpStatus.CREATED).body(new LancamentoDto(lancamento));
    }

    @PutMapping("/{lancamentoId}")
    public ResponseEntity<LancamentoDto> atualizarLancamento(@PathVariable Integer lancamentoId, @RequestBody LancamentoAtualizacaoForm lancamentoAtualizacaoForm){

        Lancamento lancamento = this.atualizarLancamentoUseCase.atualizar(lancamentoId, lancamentoAtualizacaoForm);

        return ResponseEntity.ok(new LancamentoDto(lancamento));
    }

    @DeleteMapping("/{lancamentoId}")
    public ResponseEntity<?> removerLancamento(@PathVariable Integer lancamentoId) {

        this.removerLancamentoUseCase.remover(lancamentoId);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<LancamentoDto>> listarLancamentos() {
        Usuario usuario = UsuarioContext.getUsuario();
        List<Lancamento> listaLancamentos = this.buscarTodosLancamentosUseCase.buscarTodos(usuario);

        List<LancamentoDto> lista = listaLancamentos.stream().map(LancamentoDto::new).collect(Collectors.toList());

        return ResponseEntity.ok(lista);

    }

    @GetMapping("/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<LancamentoDto>> listarLancamentosDoMes(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicial, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFinal) {
        List<Lancamento> listaLancamentos = this.buscarTodosLancamentoPorDataUseCase.buscarTodos(dataInicial, dataFinal);

        List<LancamentoDto> lista = listaLancamentos.stream().map(LancamentoDto::new).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }
}
