package br.eckelp.lancamentoconta.formaPagamento.controller;

import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formaPagamento.dominio.interfaces.IAtualizarFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formaPagamento.dominio.interfaces.IBuscarTodasFormasPagamentoUseCase;
import br.eckelp.lancamentoconta.formaPagamento.dominio.interfaces.ICadastrarFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formaPagamento.dominio.interfaces.IRemoverFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formaPagamento.useCase.BuscarTodasFormasPagamentoUseCase;
import br.eckelp.lancamentoconta.formaPagamento.useCase.RemoverFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formaPagamento.useCase.AtualizarFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formaPagamento.useCase.CadastrarFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formaPagamento.dominio.dto.FormaPagamentoAtualizacaoForm;
import br.eckelp.lancamentoconta.formaPagamento.dominio.dto.FormaPagamentoCadastroForm;
import br.eckelp.lancamentoconta.formaPagamento.dominio.dto.FormaPagamentoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

    private final ICadastrarFormaPagamentoUseCase cadastrarFormaPagamentoUseCase;
    private final IAtualizarFormaPagamentoUseCase atualizarFormaPagamentoUseCase;
    private final IBuscarTodasFormasPagamentoUseCase buscarFormaPagamentoUseCase;
    private final IRemoverFormaPagamentoUseCase removerFormaPagamentoUseCase;

    public FormaPagamentoController(ICadastrarFormaPagamentoUseCase cadastrarFormaPagamentoUseCase,
                                    IAtualizarFormaPagamentoUseCase atualizarFormaPagamentoUseCase,
                                    IBuscarTodasFormasPagamentoUseCase buscarFormaPagamentoUseCase,
                                    IRemoverFormaPagamentoUseCase removerFormaPagamentoUseCase) {
        this.cadastrarFormaPagamentoUseCase = cadastrarFormaPagamentoUseCase;
        this.atualizarFormaPagamentoUseCase = atualizarFormaPagamentoUseCase;
        this.buscarFormaPagamentoUseCase = buscarFormaPagamentoUseCase;
        this.removerFormaPagamentoUseCase = removerFormaPagamentoUseCase;
    }


    @PostMapping
    public ResponseEntity<FormaPagamentoDto> criarFormaPagamento(@RequestBody FormaPagamentoCadastroForm formaPagamentoCadastroForm) {

        FormaPagamento formaPagamento = this.cadastrarFormaPagamentoUseCase.cadastrar(formaPagamentoCadastroForm);

        return ResponseEntity.status(HttpStatus.CREATED).body(new FormaPagamentoDto(formaPagamento));
    }

    @PutMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamentoDto> atualizarFormaPagamento(@PathVariable Integer formaPagamentoId, @RequestBody FormaPagamentoAtualizacaoForm formaPagamentoAtualizacaoForm) {

        FormaPagamento formaPagamento = this.atualizarFormaPagamentoUseCase.atualizar(formaPagamentoId, formaPagamentoAtualizacaoForm);

        return ResponseEntity.ok(new FormaPagamentoDto(formaPagamento));
    }

    @DeleteMapping("/{formaPagamentoId}")
    public ResponseEntity<?> removerFormaPagamento(@PathVariable Integer formaPagamentoId) {

        this.removerFormaPagamentoUseCase.remover(formaPagamentoId);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<FormaPagamentoDto>> listarFormasPagamento() {
        List<FormaPagamento> listaFormasPagamento = this.buscarFormaPagamentoUseCase.buscarTodas();

        List<FormaPagamentoDto> lista = listaFormasPagamento.stream().map(FormaPagamentoDto::new).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }
}
