package br.eckelp.lancamentoconta.service;

import br.eckelp.lancamentoconta.domain.dto.LancamentoCadastroDTO;
import br.eckelp.lancamentoconta.domain.model.Categoria;
import br.eckelp.lancamentoconta.domain.model.FormaPagamento;
import br.eckelp.lancamentoconta.domain.model.Lancamento;
import br.eckelp.lancamentoconta.repository.LancamentoRepository;
import br.eckelp.lancamentoconta.service.validacao.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class LancamentoService {

    private final List<ValidacaoLancamento> validacoes;
    private final LancamentoRepository repository;

    public LancamentoService(LancamentoRepository repository,
                             ValidacaoLancamentoCategoriaExistenteService validacaoLancamentoCategoriaExistente,
                             ValidacaoLancamentoFormaPagamentoExistenteService validacaoLancamentoFormaPagamentoExistente,
                             ValidacaoLancamentoValorMaiorQueZeroService validacaoLancamentoValorMaiorQueZero ,
                             ValidacaoLancamentoDescricaoPreenchidaService validacaoLancamentoDescricaoPreenchida) {
        this.repository = repository;

        this.validacoes = Arrays.asList(
                validacaoLancamentoCategoriaExistente
               ,validacaoLancamentoFormaPagamentoExistente
               ,validacaoLancamentoValorMaiorQueZero
               ,validacaoLancamentoDescricaoPreenchida
        );
    }

    private Lancamento criarLancamento(Lancamento lancamento) {
        this.validarLancamento(lancamento);
        return this.repository.save(lancamento);
    }

    public LancamentoCadastroDTO criarLancamento(LancamentoCadastroDTO lancamentoCadastroDTO) {

        Categoria categoria = new Categoria(lancamentoCadastroDTO.getCategoriaId());
        FormaPagamento formaPagamento = new FormaPagamento(lancamentoCadastroDTO.getFormaPagamentoId());

        Lancamento lancamento = Lancamento.builder()
                .categoria(categoria)
                .formaPagamento(formaPagamento)
                .descricao(lancamentoCadastroDTO.getDescricao())
                .valor(lancamentoCadastroDTO.getValor())
                .data(lancamentoCadastroDTO.getData())
                .build();

        Lancamento novoLancamento = this.criarLancamento(lancamento);

        lancamentoCadastroDTO.setId(novoLancamento.getId());

        return lancamentoCadastroDTO;
    }

    public void validarLancamento(Lancamento lancamento){
        this.validacoes.forEach(validacao -> validacao.validar(lancamento));
    }
}
