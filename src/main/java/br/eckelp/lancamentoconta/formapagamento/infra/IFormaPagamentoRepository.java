package br.eckelp.lancamentoconta.formapagamento.infra;

import br.eckelp.lancamentoconta.app.security.dominio.Usuario;
import br.eckelp.lancamentoconta.formapagamento.dominio.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFormaPagamentoRepository extends JpaRepository<FormaPagamento, Integer> {

    @Query("SELECT fp " +
            " FROM FormaPagamento fp WHERE fp.usuario = :usuario")
    List<FormaPagamento> listarFormasPagamento(Usuario usuario);

    @Override
    @Modifying
    @Query("DELETE FROM FormaPagamento p WHERE p.id = :formaPagamentoId")
    void deleteById(Integer formaPagamentoId);
}
