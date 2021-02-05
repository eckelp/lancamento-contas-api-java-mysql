package br.eckelp.lancamentoconta.categoria.useCase.validacao;

import br.eckelp.lancamentoconta.categoria.dominio.interfaces.IValidacaoCategoriaUseCase;
import br.eckelp.lancamentoconta.sistema.infra.exception.ArgumentoInvalidoException;
import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import org.springframework.stereotype.Service;

@Service
public class ValidacaoCategoriaDescricaoPreenchidaUseCase implements IValidacaoCategoriaUseCase {


    @Override
    public void validar(Categoria categoria) {

        String descricao = categoria.getDescricao();

        if(descricao == null || descricao.isEmpty()){
            throw new ArgumentoInvalidoException("A descrição não pode ser vazia");
        }
    }
}
