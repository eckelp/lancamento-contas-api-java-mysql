package br.eckelp.lancamentoconta.lancamento.dominio.dto;

import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LancamentoAtualizacaoForm  {

    private Integer id;

    private String descricao;

    private Integer categoriaId;

    private Integer formaPagamentoId;

    private BigDecimal valor;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate data = LocalDate.now();

    public LocalDate getData() {
        return data == null ? LocalDate.now() : data;
    }

    public Lancamento criarLancamento(Integer lancamentoId){
        return Lancamento.builder()
                .id(lancamentoId)
                .categoria(new Categoria(categoriaId))
                .formaPagamento(new FormaPagamento(formaPagamentoId))
                .descricao(descricao)
                .valor(valor)
                .data(data)
                .build();
    }

    public Lancamento criarLancamento(){
        return criarLancamento(id);
    }



}
