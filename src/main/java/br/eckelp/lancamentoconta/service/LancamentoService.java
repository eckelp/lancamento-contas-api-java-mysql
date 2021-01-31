package br.eckelp.lancamentoconta.service;

import br.eckelp.lancamentoconta.domain.dto.lancamento.ILancamentoDTO;
import br.eckelp.lancamentoconta.domain.dto.lancamento.LancamentoAtualizacaoDTO;
import br.eckelp.lancamentoconta.domain.dto.lancamento.LancamentoCadastroDTO;
import br.eckelp.lancamentoconta.domain.dto.lancamento.LancamentoListagemDTO;
import br.eckelp.lancamentoconta.domain.exception.ObjetoNaoEncontradoException;
import br.eckelp.lancamentoconta.domain.model.Categoria;
import br.eckelp.lancamentoconta.domain.model.FormaPagamento;
import br.eckelp.lancamentoconta.domain.model.Lancamento;
import br.eckelp.lancamentoconta.repository.LancamentoRepository;
import br.eckelp.lancamentoconta.service.validacao.lancamento.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class LancamentoService {

    private final List<ValidacaoLancamento> validacoes;
    private final LancamentoRepository repository;

    public LancamentoService(LancamentoRepository repository,
                             ValidacaoLancamentoCategoriaExistenteService validacaoLancamentoCategoriaExistente,
                             ValidacaoLancamentoFormaPagamentoExistenteService validacaoLancamentoFormaPagamentoExistente,
                             ValidacaoLancamentoValorMaiorQueZeroService validacaoLancamentoValorMaiorQueZero,
                             ValidacaoLancamentoDescricaoPreenchidaService validacaoLancamentoDescricaoPreenchida) {
        this.repository = repository;

        this.validacoes = Arrays.asList(
                validacaoLancamentoCategoriaExistente
                , validacaoLancamentoFormaPagamentoExistente
                , validacaoLancamentoValorMaiorQueZero
                , validacaoLancamentoDescricaoPreenchida
        );
    }

    public LancamentoCadastroDTO criarLancamento(LancamentoCadastroDTO lancamentoCadastroDTO) {

        Lancamento lancamento = this.getLancamento(lancamentoCadastroDTO);

        Lancamento novoLancamento = this.salvarLancamento(lancamento);

        lancamentoCadastroDTO.setId(novoLancamento.getId());

        return lancamentoCadastroDTO;
    }

    public LancamentoAtualizacaoDTO atualizarLancamento(Integer lancamentoId, LancamentoAtualizacaoDTO lancamentoAtualizacaoDTO) {
        this.verificarSeLancamentoExiste(lancamentoId);

        Lancamento lancamento = this.getLancamento(lancamentoId, lancamentoAtualizacaoDTO);

        this.salvarLancamento(lancamento);

        return lancamentoAtualizacaoDTO;
    }

    private void verificarSeLancamentoExiste(Integer lancamentoId) {
        this.buscarLancamentoPorId(lancamentoId);
    }

    private Lancamento buscarLancamentoPorId(Integer lancamentoId) {
        Optional<Lancamento> lancamentoOptional = this.repository.findById(lancamentoId);

        return lancamentoOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Lançamento não encontrado"));
    }

    private Lancamento salvarLancamento(Lancamento lancamento) {
        this.validarLancamento(lancamento);
        return this.repository.save(lancamento);
    }

    private Lancamento getLancamento(Integer lancamentoId, ILancamentoDTO lancamentoDTO) {

        Categoria categoria = new Categoria(lancamentoDTO.getCategoriaId());
        FormaPagamento formaPagamento = new FormaPagamento(lancamentoDTO.getFormaPagamentoId());

        return Lancamento.builder()
                .id(lancamentoId)
                .categoria(categoria)
                .formaPagamento(formaPagamento)
                .descricao(lancamentoDTO.getDescricao())
                .valor(lancamentoDTO.getValor())
                .data(lancamentoDTO.getData())
                .build();
    }

    private Lancamento getLancamento(ILancamentoDTO lancamentoDTO) {
        return getLancamento(null, lancamentoDTO);
    }

    private void validarLancamento(Lancamento lancamento) {
        this.validacoes.forEach(validacao -> validacao.validar(lancamento));
    }


    public void removerLancamento(Integer lancamentoId) {
        this.repository.deleteById(lancamentoId);
    }

    public List<LancamentoListagemDTO> listarLancamentos(LocalDate dataInicial, LocalDate dataFinal) {

        List<LancamentoListagemDTO> listaLancamentos = this.repository.listarLancamentos(dataInicial, dataFinal);

        return listaLancamentos;
    }
}
