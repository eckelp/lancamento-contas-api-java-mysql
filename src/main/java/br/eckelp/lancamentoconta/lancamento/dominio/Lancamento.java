package br.eckelp.lancamentoconta.lancamento.dominio;

import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.formaPagamento.dominio.FormaPagamento;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "lancamento")
@Data
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

    @Transient
    public String getDescricaoCategoria() {
        return categoria != null ? categoria.getDescricao() : "";
    }

    @Transient
    public String getDescricaoFormaPagamento() {
        return formaPagamento != null ? formaPagamento.getDescricao() : "";
    }
}
