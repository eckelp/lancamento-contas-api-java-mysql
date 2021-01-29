package br.eckelp.lancamentoconta.resource;

import br.eckelp.lancamentoconta.domain.dto.LancamentoCadastroDTO;
import br.eckelp.lancamentoconta.service.LancamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        return ResponseEntity.ok().build();
    }
}
