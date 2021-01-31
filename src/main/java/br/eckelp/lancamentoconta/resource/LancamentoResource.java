package br.eckelp.lancamentoconta.resource;

import br.eckelp.lancamentoconta.domain.dto.lancamento.LancamentoAtualizacaoDTO;
import br.eckelp.lancamentoconta.domain.dto.lancamento.LancamentoCadastroDTO;
import br.eckelp.lancamentoconta.domain.dto.lancamento.LancamentoListagemDTO;
import br.eckelp.lancamentoconta.service.LancamentoService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    private final LancamentoService lancamentoService;

    public LancamentoResource(LancamentoService lancamentoService) {
        this.lancamentoService = lancamentoService;
    }

    @PostMapping
    public ResponseEntity<?> criarLancamento(@RequestBody LancamentoCadastroDTO lancamentoCadastroDTO){
        this.lancamentoService.criarLancamento(lancamentoCadastroDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{lancamentoId}")
    public ResponseEntity<?> atualizarLancamento(@PathVariable Integer lancamentoId, @RequestBody LancamentoAtualizacaoDTO lancamentoAtualizacaoDTO){
        this.lancamentoService.atualizarLancamento(lancamentoId, lancamentoAtualizacaoDTO);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{lancamentoId}")
    public ResponseEntity<?> removerLancamento(@PathVariable Integer lancamentoId) {

        try {
            this.lancamentoService.removerLancamento(lancamentoId);
        } catch (EmptyResultDataAccessException e) {
            //Se não encontrar o registro então a função do endpoint também foi realizada: Não ter mais o recurso informado na base
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{dataInicial}/{dataFinal}")
    public ResponseEntity<List<LancamentoListagemDTO>> listarLancamentosDoMes(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicial, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFinal) {
        List<LancamentoListagemDTO> listaLancamentos = this.lancamentoService.listarLancamentos(dataInicial, dataFinal);

        return ResponseEntity.ok(listaLancamentos);
    }
}
