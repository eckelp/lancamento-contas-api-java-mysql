package br.eckelp.lancamentoconta.service;

import br.eckelp.lancamentoconta.domain.exception.ObjetoNaoEncontradoException;
import br.eckelp.lancamentoconta.domain.model.FormaPagamento;
import br.eckelp.lancamentoconta.repository.FormaPagamentoRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FormaPagamentoService {

    private final FormaPagamentoRepository repository;

    public FormaPagamentoService(FormaPagamentoRepository repository) {
        this.repository = repository;
    }

    public FormaPagamento buscarFormaPagamentoPorId(Integer formaPagamentoId)  throws ObjetoNaoEncontradoException{

        if(formaPagamentoId == null){
            throw new ObjetoNaoEncontradoException("Forma de pagamento não encontrada");
        }

        Optional<FormaPagamento> formaPagamentoOptional = this.repository.findById(formaPagamentoId);

        return formaPagamentoOptional.orElseThrow(() -> new ObjetoNaoEncontradoException("Forma de pagamento não encontrada"));
    }
}
