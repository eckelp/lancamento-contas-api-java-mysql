package br.eckelp.lancamentoconta.domain.service;

import br.eckelp.lancamentoconta.domain.exception.ObjetoNaoEncontradoException;
import br.eckelp.lancamentoconta.domain.model.Lancamento;
import br.eckelp.lancamentoconta.domain.repository.LancamentoRepository;
import br.eckelp.lancamentoconta.domain.service.validacao.lancamento.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class LancamentoService {

    private final List<ValidacaoLancamento> validacoes;
    private final LancamentoRepository repository;

    public LancamentoService(LancamentoRepository repository,
                             List<ValidacaoLancamento> validacoes) {
        this.repository = repository;

        this.validacoes = validacoes;
    }

    @Transactional
    public Lancamento criarLancamento(Lancamento lancamento) {

        Lancamento novoLancamento = this.salvarLancamento(lancamento);

        return novoLancamento;
    }

    @Transactional
    public Lancamento atualizarLancamento(Integer lancamentoId, Lancamento lancamento) {
        this.verificarSeLancamentoExiste(lancamentoId);

        lancamento = this.salvarLancamento(lancamento);

        return lancamento;
    }

    private void verificarSeLancamentoExiste(Integer lancamentoId) {
        this.buscarLancamentoPorId(lancamentoId);
    }

    private Lancamento buscarLancamentoPorId(Integer lancamentoId) {
        Optional<Lancamento> lancamentoOptional = this.repository.findById(lancamentoId);

        return lancamentoOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Lançamento não encontrado"));
    }

    @Transactional
    private Lancamento salvarLancamento(Lancamento lancamento) {
        this.validarLancamento(lancamento);

        return this.repository.saveAndFlush(lancamento);
    }

    private void validarLancamento(Lancamento lancamento) {
        this.validacoes.forEach(validacao -> validacao.validar(lancamento));
    }

    public void removerLancamento(Integer lancamentoId) {
        this.repository.deleteById(lancamentoId);
    }

    public List<Lancamento> listarLancamentos(LocalDate dataInicial, LocalDate dataFinal) {

        List<Lancamento> listaLancamentos = this.repository.listarLancamentos(dataInicial, dataFinal);

        return listaLancamentos;
    }
}
