package br.eckelp.lancamentoconta.domain.service.validacao.categoria;

import br.eckelp.lancamentoconta.domain.exception.ArgumentoInvalidoException;
import br.eckelp.lancamentoconta.domain.model.Categoria;
import org.springframework.stereotype.Service;

@Service
public class ValidacaoCategoriaDescricaoPreenchidaService implements ValidacaoCategoria{


    @Override
    public void validar(Categoria categoria) {

        String descricao = categoria.getDescricao();

        if(descricao == null || descricao.isEmpty()){
            throw new ArgumentoInvalidoException("A descrição não pode ser vazia");
        }
    }
}
