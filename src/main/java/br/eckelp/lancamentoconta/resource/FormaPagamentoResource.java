package br.eckelp.lancamentoconta.resource;

import br.eckelp.lancamentoconta.domain.dto.formaPagamento.FormaPagamentoAtualizacaoDTO;
import br.eckelp.lancamentoconta.domain.dto.formaPagamento.FormaPagamentoCadastroDTO;
import br.eckelp.lancamentoconta.domain.dto.formaPagamento.FormaPagamentoListagemDTO;
import br.eckelp.lancamentoconta.service.FormaPagamentoService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoResource {

    private final FormaPagamentoService formaPagamentoService;

    public FormaPagamentoResource(FormaPagamentoService formaPagamentoService) {
        this.formaPagamentoService = formaPagamentoService;
    }

    @PostMapping
    public ResponseEntity<?> criarFormaPagamento(@RequestBody FormaPagamentoCadastroDTO formaPagamentoCadastroDTO ){
        formaPagamentoCadastroDTO = this.formaPagamentoService.criarFormaPagamento(formaPagamentoCadastroDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(formaPagamentoCadastroDTO);
    }

    @PutMapping("/{formaPagamentoId}")
    public ResponseEntity<?> atualizarFormaPagamento(@PathVariable Integer formaPagamentoId, @RequestBody FormaPagamentoAtualizacaoDTO formaPagamentoAtualizacaoDTO){
        formaPagamentoAtualizacaoDTO = this.formaPagamentoService.atualizarFormaPagamento(formaPagamentoId, formaPagamentoAtualizacaoDTO);

        return ResponseEntity.ok(formaPagamentoAtualizacaoDTO);
    }

    @DeleteMapping("/{formaPagamentoId}")
    public ResponseEntity<?> removerFormaPagamento(@PathVariable Integer formaPagamentoId) {

        try {
            this.formaPagamentoService.removerFormaPagamento(formaPagamentoId);
        } catch (EmptyResultDataAccessException e) {
            //Se não encontrar o registro então a função do endpoint também foi realizada: Não ter mais o recurso informado na base
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<FormaPagamentoListagemDTO>> listarFormasPagamento() {
        List<FormaPagamentoListagemDTO> listaFormasPagamento = this.formaPagamentoService.listarFormasPagamento();

        return ResponseEntity.ok(listaFormasPagamento);
    }
}
