package br.eckelp.lancamentoconta.formapagamento.dominio;

import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "forma_pagamento")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descricao;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public FormaPagamento(Integer id) {
        this.id = id;
    }


    public FormaPagamento(String descricao, Usuario usuario) {
        this.descricao = descricao;
        this.usuario = usuario;

    }
}
