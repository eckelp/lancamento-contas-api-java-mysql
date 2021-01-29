package br.eckelp.lancamentoconta.service.validacao;

import br.eckelp.lancamentoconta.domain.model.Lancamento;
import br.eckelp.lancamentoconta.service.CategoriaService;
import org.springframework.stereotype.Service;

@Service
public class ValidacaoLancamentoCategoriaExistenteService implements ValidacaoLancamento{

    private final CategoriaService categoriaService;

    public ValidacaoLancamentoCategoriaExistenteService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Override
    public void validar(Lancamento lancamento) {
        this.categoriaService.buscarCategoriaPorId(lancamento.getCategoriaId());
    }
}
