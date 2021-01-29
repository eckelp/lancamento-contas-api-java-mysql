package br.eckelp.lancamentoconta.resource;

import br.eckelp.lancamentoconta.domain.dto.lancamento.LancamentoAtualizacaoDTO;
import br.eckelp.lancamentoconta.domain.dto.lancamento.LancamentoCadastroDTO;
import br.eckelp.lancamentoconta.service.LancamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
