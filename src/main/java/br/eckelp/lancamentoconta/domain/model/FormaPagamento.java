package br.eckelp.lancamentoconta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "forma_pagamento")
@Getter
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
}
