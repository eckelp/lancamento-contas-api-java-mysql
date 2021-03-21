package br.eckelp.lancamentoconta.lancamento.dominio.dto;

import br.eckelp.lancamentoconta.app.security.controller.dto.UsuarioDto;
import br.eckelp.lancamentoconta.categoria.dominio.dto.CategoriaDto;
import br.eckelp.lancamentoconta.formapagamento.dominio.dto.FormaPagamentoDto;
import br.eckelp.lancamentoconta.lancamento.dominio.Lancamento;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoDto {

    private Integer id;

    private LocalDate data;

    private String descricao;

    private BigDecimal valor;

    private CategoriaDto categoria;

    private FormaPagamentoDto formaPagamento;

    private UsuarioDto usuario;

    public LancamentoDto(Lancamento lancamento) {
        this.id = lancamento.getId();
        this.data = lancamento.getData();
        this.descricao = lancamento.getDescricao();
        this.valor = lancamento.getValor();
        this.categoria = new CategoriaDto(lancamento.getCategoriaId(), lancamento.getDescricaoCategoria());
        this.formaPagamento = new FormaPagamentoDto(lancamento.getFormaPagamentoId(), lancamento.getDescricaoFormaPagamento());
        this.usuario = UsuarioDto.builder().id(lancamento.getUsuarioId()).nome(lancamento.getUsuarioNome()).build();
    }
}
