package br.eckelp.lancamentoconta.categoria.dominio;

import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "categoria")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descricao;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Categoria(Integer id) {
        this.id = id;
    }

    public Categoria(String descricao, Usuario usuario) {
        this.descricao = descricao;
        this.usuario = usuario;
    }
}
