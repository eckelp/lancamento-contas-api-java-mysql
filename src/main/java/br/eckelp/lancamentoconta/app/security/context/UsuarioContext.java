package br.eckelp.lancamentoconta.app.security.context;

import br.eckelp.lancamentoconta.app.security.dominio.Usuario;

public class UsuarioContext {

    private static final InheritableThreadLocal<Usuario> USUARIO = new InheritableThreadLocal<>();

    private UsuarioContext(){}

    public static void setUsuario(Usuario usuario) {
        USUARIO.set(usuario);
    }

    public static Usuario getUsuario(){
        return USUARIO.get();
    }

}
