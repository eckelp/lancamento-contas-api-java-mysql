package br.eckelp.lancamentoconta.domain.repository;

import br.eckelp.lancamentoconta.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Integer> {

    @Query("SELECT fp " +
            " FROM FormaPagamento fp")
    List<FormaPagamento> listarFormasPagamento();

    @Override
    @Modifying
    @Query("DELETE FROM FormaPagamento p WHERE p.id = :formaPagamentoId")
    void deleteById(Integer formaPagamentoId);
}
