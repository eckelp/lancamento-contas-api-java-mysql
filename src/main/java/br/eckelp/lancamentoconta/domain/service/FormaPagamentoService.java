package br.eckelp.lancamentoconta.domain.service;

import br.eckelp.lancamentoconta.domain.exception.ObjetoNaoEncontradoException;
import br.eckelp.lancamentoconta.domain.model.FormaPagamento;
import br.eckelp.lancamentoconta.domain.repository.FormaPagamentoRepository;
import br.eckelp.lancamentoconta.domain.service.validacao.formaPagamento.ValidacaoFormaPagamento;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FormaPagamentoService {

    private final FormaPagamentoRepository repository;

    private final List<ValidacaoFormaPagamento> validacoes;

    public FormaPagamentoService(FormaPagamentoRepository repository, List<ValidacaoFormaPagamento> validacoes) {
        this.repository = repository;
        this.validacoes = validacoes;
    }

    public FormaPagamento buscarFormaPagamentoPorId(Integer formaPagamentoId)  throws ObjetoNaoEncontradoException{

        if(formaPagamentoId == null){
            throw new ObjetoNaoEncontradoException("Forma de pagamento não encontrada");
        }

        Optional<FormaPagamento> formaPagamentoOptional = this.repository.findById(formaPagamentoId);

        return formaPagamentoOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Forma de pagamento não encontrada"));
    }

    @Transactional
    public FormaPagamento criarFormaPagamento(FormaPagamento formaPagamento) {

        formaPagamento = this.salvarFormaPagamento(formaPagamento);

        return formaPagamento;
    }

    @Transactional
    public FormaPagamento atualizarFormaPagamento(Integer formaPagamentoId, FormaPagamento formaPagamento) {

        this.verificarSeFormaPagamentoExiste(formaPagamentoId);

        formaPagamento = this.salvarFormaPagamento(formaPagamento);

        return formaPagamento;
    }

    public void removerFormaPagamento(Integer formaPagamentoId) {
        this.repository.deleteById(formaPagamentoId);
    }

    public List<FormaPagamento> listarFormasPagamento() {

        List<FormaPagamento> listaFormasPagamento = this.repository.listarFormasPagamento();

        return listaFormasPagamento;
    }

    private void verificarSeFormaPagamentoExiste(Integer formaPagamentoId){
        this.buscarFormaPagamentoPorId(formaPagamentoId);
    }

    private FormaPagamento salvarFormaPagamento(FormaPagamento formaPagamento) {
        this.valicarFormaPagamento(formaPagamento);

        return this.repository.save(formaPagamento);
    }

    private void valicarFormaPagamento(FormaPagamento formaPagamento) {
        this.validacoes.forEach(validacao -> validacao.validar(formaPagamento));
    }

}
