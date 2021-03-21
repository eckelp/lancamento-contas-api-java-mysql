package br.eckelp.lancamentoconta.lancamento.dominio;

import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.formapagamento.dominio.FormaPagamento;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

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

    public Long getUsuarioId() {
        return usuario != null ? usuario.getId() : null;
    }

    public String getUsuarioNome(){
        return usuario != null ? usuario.getNome() : null;
    }
}
