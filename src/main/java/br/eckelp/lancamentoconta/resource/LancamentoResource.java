package br.eckelp.lancamentoconta.resource;

import br.eckelp.lancamentoconta.domain.model.Lancamento;
import br.eckelp.lancamentoconta.resource.dto.lancamento.LancamentoAtualizacaoForm;
import br.eckelp.lancamentoconta.resource.dto.lancamento.LancamentoCadastroForm;
import br.eckelp.lancamentoconta.resource.dto.lancamento.LancamentoDto;
import br.eckelp.lancamentoconta.domain.service.LancamentoService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    private final LancamentoService lancamentoService;

    public LancamentoResource(LancamentoService lancamentoService) {
        this.lancamentoService = lancamentoService;
    }

    @PostMapping
    public ResponseEntity<LancamentoDto> criarLancamento(@RequestBody LancamentoCadastroForm lancamentoCadastroForm){
        Lancamento lancamento = lancamentoCadastroForm.converter();

        lancamento = this.lancamentoService.criarLancamento(lancamento);

        return ResponseEntity.status(HttpStatus.CREATED).body(new LancamentoDto(lancamento));
    }

    @PutMapping("/{lancamentoId}")
    public ResponseEntity<LancamentoDto> atualizarLancamento(@PathVariable Integer lancamentoId, @RequestBody LancamentoAtualizacaoForm lancamentoAtualizacaoForm){

        Lancamento lancamento = lancamentoAtualizacaoForm.converter(lancamentoId);

        lancamento = this.lancamentoService.atualizarLancamento(lancamentoId, lancamento);

        return ResponseEntity.ok(new LancamentoDto(lancamento));
    }

    @DeleteMapping("/{lancamentoId}")
    public ResponseEntity<?> removerLancamento(@PathVariable Integer lancamentoId) {

        this.lancamentoService.removerLancamento(lancamentoId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<LancamentoDto>> listarLancamentosDoMes(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicial, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFinal) {
        List<Lancamento> listaLancamentos = this.lancamentoService.listarLancamentos(dataInicial, dataFinal);

        List<LancamentoDto> lista = listaLancamentos.stream().map(lancamento -> new LancamentoDto(lancamento)).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }
}
