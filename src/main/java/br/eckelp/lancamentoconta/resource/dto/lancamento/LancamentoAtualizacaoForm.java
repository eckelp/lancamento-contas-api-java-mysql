package br.eckelp.lancamentoconta.resource.dto.lancamento;

import br.eckelp.lancamentoconta.domain.model.Categoria;
import br.eckelp.lancamentoconta.domain.model.FormaPagamento;
import br.eckelp.lancamentoconta.domain.model.Lancamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

    public Lancamento converter(Integer lancamentoId){
        return Lancamento.builder()
                .id(lancamentoId)
                .categoria(new Categoria(categoriaId))
                .formaPagamento(new FormaPagamento(formaPagamentoId))
                .descricao(descricao)
                .valor(valor)
                .data(data)
                .build();
    }

    public Lancamento converter(){
        return converter(id);
    }



}
