package br.eckelp.lancamentoconta.lancamento.dominio.dto;

import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LancamentoCadastroForm {

    private Integer id;

    private String descricao;

    private Integer categoriaId;

    private Integer formaPagamentoId;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate data = LocalDate.now();

    private BigDecimal valor;

    public LocalDate getData() {
        return data == null ? LocalDate.now() : data;
    }

    public Lancamento criarLancamento() {
        return Lancamento.builder()
                .id(id)
                .descricao(descricao)
                .categoria(new Categoria(categoriaId))
                .formaPagamento(new FormaPagamento(formaPagamentoId))
                .data(data)
                .valor(valor)
                .build();
    }
}
