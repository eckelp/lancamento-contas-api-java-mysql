package br.eckelp.lancamentoconta.formaPagamento.dominio;

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

    public FormaPagamento(Integer id) {
        this.id = id;
    }

    public FormaPagamento(String descricao) {
        this.descricao = descricao;
    }
}
