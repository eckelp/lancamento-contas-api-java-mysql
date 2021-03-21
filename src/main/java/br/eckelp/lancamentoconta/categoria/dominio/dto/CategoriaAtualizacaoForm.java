package br.eckelp.lancamentoconta.categoria.dominio.dto;

import br.eckelp.lancamentoconta.app.security.context.UsuarioContext;
import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaAtualizacaoForm {

    private Integer id;

    private String descricao;

    public Categoria criarCategoria(Integer categoriaId, Usuario usuario) {
        return Categoria.builder()
                .id(categoriaId)
                .descricao(descricao)
                .usuario(usuario)
                .build();
    }

    public CategoriaAtualizacaoForm(String descricao) {
        this.descricao = descricao;
    }
}
