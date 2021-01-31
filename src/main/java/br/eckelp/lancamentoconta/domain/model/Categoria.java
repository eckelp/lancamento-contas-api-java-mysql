package br.eckelp.lancamentoconta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "categoria")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descricao;

    public Categoria(Integer id) {
        this.id = id;
    }

    public Categoria(String descricao) {
        this.descricao = descricao;
    }
}
