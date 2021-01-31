package br.eckelp.lancamentoconta.service;

import br.eckelp.lancamentoconta.domain.dto.formaPagamento.FormaPagamentoAtualizacaoDTO;
import br.eckelp.lancamentoconta.domain.dto.formaPagamento.FormaPagamentoCadastroDTO;
import br.eckelp.lancamentoconta.domain.dto.formaPagamento.FormaPagamentoListagemDTO;
import br.eckelp.lancamentoconta.domain.dto.formaPagamento.IFormaPagamentoDTO;
import br.eckelp.lancamentoconta.domain.exception.ObjetoNaoEncontradoException;
import br.eckelp.lancamentoconta.domain.model.FormaPagamento;
import br.eckelp.lancamentoconta.repository.FormaPagamentoRepository;
import br.eckelp.lancamentoconta.service.validacao.formaPagamento.ValidacaoFormaPagamento;
import br.eckelp.lancamentoconta.service.validacao.formaPagamento.ValidacaoFormaPagamentoDescricaoPreenchidaService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class FormaPagamentoService {

    private final FormaPagamentoRepository repository;

    private final List<ValidacaoFormaPagamento> validacoes;

    public FormaPagamentoService(FormaPagamentoRepository repository, ValidacaoFormaPagamentoDescricaoPreenchidaService validacaoFormaPagamentoDescricaoPreenchidaService) {
        this.repository = repository;
        this.validacoes = Arrays.asList(validacaoFormaPagamentoDescricaoPreenchidaService);
    }

    public FormaPagamento buscarFormaPagamentoPorId(Integer formaPagamentoId)  throws ObjetoNaoEncontradoException{

        if(formaPagamentoId == null){
            throw new ObjetoNaoEncontradoException("Forma de pagamento não encontrada");
        }

        Optional<FormaPagamento> formaPagamentoOptional = this.repository.findById(formaPagamentoId);

        return formaPagamentoOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Forma de pagamento não encontrada"));
    }

    public FormaPagamentoCadastroDTO criarFormaPagamento(FormaPagamentoCadastroDTO formaPagamentoCadastroDTO) {
        FormaPagamento formaPagamento = this.getFormaPagamento(formaPagamentoCadastroDTO);

        FormaPagamento novaFormaPagamento = this.salvarFormaPagamento(formaPagamento);

        formaPagamentoCadastroDTO.setId(novaFormaPagamento.getId());

        return formaPagamentoCadastroDTO;
    }

    public FormaPagamentoAtualizacaoDTO atualizarFormaPagamento(Integer formaPagamentoId, FormaPagamentoAtualizacaoDTO formaPagamentoAtualizacaoDTO) {

        this.verificarSeFormaPagamentoExiste(formaPagamentoId);

        FormaPagamento formaPagamento = this.getFormaPagamento(formaPagamentoId, formaPagamentoAtualizacaoDTO);

        formaPagamento = this.salvarFormaPagamento(formaPagamento);

        formaPagamentoAtualizacaoDTO.setId(formaPagamento.getId());

        return formaPagamentoAtualizacaoDTO;
    }

    public void removerFormaPagamento(Integer formaPagamentoId) {
        this.repository.deleteById(formaPagamentoId);
    }

    public List<FormaPagamentoListagemDTO> listarFormasPagamento() {

        List<FormaPagamentoListagemDTO> listaFormasPagamento = this.repository.listarFormasPagamento();

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

    private FormaPagamento getFormaPagamento(Integer categoiaId, IFormaPagamentoDTO iFormaPagamento) {
        FormaPagamento formaPagamento = FormaPagamento.builder()
                .id(categoiaId)
                .descricao(iFormaPagamento.getDescricao())
                .build();

        return formaPagamento;
    }

    private FormaPagamento getFormaPagamento(IFormaPagamentoDTO iFormaPagamento) {
        return getFormaPagamento(null, iFormaPagamento);
    }
}
