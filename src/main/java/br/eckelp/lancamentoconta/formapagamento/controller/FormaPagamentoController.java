package br.eckelp.lancamentoconta.formapagamento.controller;

import br.eckelp.lancamentoconta.app.security.context.UsuarioContext;
import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.formapagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.formapagamento.dominio.dto.FormaPagamentoAtualizacaoForm;
import br.eckelp.lancamentoconta.formapagamento.dominio.dto.FormaPagamentoCadastroForm;
import br.eckelp.lancamentoconta.formapagamento.dominio.dto.FormaPagamentoDto;
import br.eckelp.lancamentoconta.formapagamento.dominio.interfaces.IAtualizarFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formapagamento.dominio.interfaces.IBuscarTodasFormasPagamentoUseCase;
import br.eckelp.lancamentoconta.formapagamento.dominio.interfaces.ICadastrarFormaPagamentoUseCase;
import br.eckelp.lancamentoconta.formapagamento.dominio.interfaces.IRemoverFormaPagamentoUseCase;
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
        Usuario usuario = UsuarioContext.getUsuario();
        List<FormaPagamento> listaFormasPagamento = this.buscarFormaPagamentoUseCase.buscarTodas(usuario);

        List<FormaPagamentoDto> lista = listaFormasPagamento.stream().map(FormaPagamentoDto::new).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }
}
