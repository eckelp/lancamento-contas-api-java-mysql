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

    public Lancamento converter() {
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
