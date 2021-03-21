package br.eckelp.lancamentoconta.categoria.usecase.validacao;

import br.eckelp.lancamentoconta.categoria.dominio.Categoria;
import br.eckelp.lancamentoconta.categoria.dominio.interfaces.IValidacaoCategoriaUseCase;
import br.eckelp.lancamentoconta.categoria.dominio.interfaces.IValidadorCategoriaUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidadorCategoriaUseCase implements IValidadorCategoriaUseCase {

    private final List<IValidacaoCategoriaUseCase> validacoes;

    public ValidadorCategoriaUseCase(List<IValidacaoCategoriaUseCase> validacoes) {
        this.validacoes = validacoes;
    }

    @Override
    public void executar(Categoria categoria) {
        validacoes.forEach(validacao -> validacao.validar(categoria));
    }
}
