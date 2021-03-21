package br.eckelp.lancamentoconta.categoria.dominio.dto;

import br.eckelp.lancamentoconta.app.security.context.UsuarioContext;
import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoriaCadastroForm {

    private Integer id;

    private String descricao;

    public Categoria criarCategoria(Usuario usuario) {
        Categoria categoria = Categoria.builder()
                .id(id)
                .descricao(descricao)
                .usuario(usuario)
                .build();

        return categoria;
    }

    public CategoriaCadastroForm(String descricao) {
        this.descricao = descricao;
    }
}
