package br.eckelp.lancamentoconta.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class LancamentoCadastroDTO {

    private Integer id;

    private String descricao;

    private Integer categoriaId;

    private Integer formaPagamentoId;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate data;

    private BigDecimal valor;

}
