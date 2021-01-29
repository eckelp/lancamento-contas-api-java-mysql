package br.eckelp.lancamentoconta.domain.model;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "lancamento")
@Getter
@Builder
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "forma_pagamento_id")
    private FormaPagamento formaPagamento;

    @Builder.Default
    private LocalDate data = LocalDate.now();

    private String descricao;

    private BigDecimal valor;

    public Integer getFormaPagamentoId() {
        return this.formaPagamento.getId();
    }

    public Integer getCategoriaId() {
        return this.categoria.getId();
    }
}
