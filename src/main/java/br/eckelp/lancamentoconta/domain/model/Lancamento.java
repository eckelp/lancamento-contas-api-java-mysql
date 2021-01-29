package br.eckelp.lancamentoconta.domain.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "lancamento")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(nullable = false)
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
