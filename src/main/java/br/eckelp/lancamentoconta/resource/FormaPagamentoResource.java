package br.eckelp.lancamentoconta.resource;

import br.eckelp.lancamentoconta.domain.model.FormaPagamento;
import br.eckelp.lancamentoconta.domain.service.FormaPagamentoService;
import br.eckelp.lancamentoconta.resource.dto.formaPagamento.FormaPagamentoAtualizacaoForm;
import br.eckelp.lancamentoconta.resource.dto.formaPagamento.FormaPagamentoCadastroForm;
import br.eckelp.lancamentoconta.resource.dto.formaPagamento.FormaPagamentoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoResource {

    private final FormaPagamentoService formaPagamentoService;

    public FormaPagamentoResource(FormaPagamentoService formaPagamentoService) {
        this.formaPagamentoService = formaPagamentoService;
    }

    @PostMapping
    public ResponseEntity<FormaPagamentoDto> criarFormaPagamento(@RequestBody FormaPagamentoCadastroForm formaPagamentoCadastroForm) {
        FormaPagamento formaPagamento = formaPagamentoCadastroForm.converter();

        formaPagamento = this.formaPagamentoService.criarFormaPagamento(formaPagamento);

        return ResponseEntity.status(HttpStatus.CREATED).body(new FormaPagamentoDto(formaPagamento));
    }

    @PutMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamentoDto> atualizarFormaPagamento(@PathVariable Integer formaPagamentoId, @RequestBody FormaPagamentoAtualizacaoForm formaPagamentoAtualizacaoForm) {
        FormaPagamento formaPagamento = formaPagamentoAtualizacaoForm.converter(formaPagamentoId);

        formaPagamento = this.formaPagamentoService.atualizarFormaPagamento(formaPagamentoId, formaPagamento);

        return ResponseEntity.ok(new FormaPagamentoDto(formaPagamento));
    }

    @DeleteMapping("/{formaPagamentoId}")
    public ResponseEntity<?> removerFormaPagamento(@PathVariable Integer formaPagamentoId) {

        this.formaPagamentoService.removerFormaPagamento(formaPagamentoId);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<FormaPagamentoDto>> listarFormasPagamento() {
        List<FormaPagamento> listaFormasPagamento = this.formaPagamentoService.listarFormasPagamento();

        List<FormaPagamentoDto> lista = listaFormasPagamento.stream().map(formaPagamento -> new FormaPagamentoDto(formaPagamento)).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }
}
