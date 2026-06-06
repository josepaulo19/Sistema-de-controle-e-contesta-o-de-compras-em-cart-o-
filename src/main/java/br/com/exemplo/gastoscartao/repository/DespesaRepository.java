package br.com.exemplo.gastoscartao.repository;

import br.com.exemplo.gastoscartao.entity.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    List<Despesa> findByCartaoId(Long cartaoId);

    @Query("select d from Despesa d where d.cartao.pessoa.id = :pessoaId")
    List<Despesa> findByPessoaId(@Param("pessoaId") Long pessoaId);

    @Query("select d from Despesa d where d.cartao.pessoa.id = :pessoaId and d.dataCompra between :inicio and :fim")
    List<Despesa> findByPessoaIdAndPeriodo(@Param("pessoaId") Long pessoaId, @Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);

    @Query("select coalesce(sum(d.valor), 0) from Despesa d where d.cartao.pessoa.id = :pessoaId")
    BigDecimal somarGastosPorPessoa(@Param("pessoaId") Long pessoaId);

    @Query("select count(d) from Despesa d where d.cartao.pessoa.id = :pessoaId")
    Long contarDespesasPorPessoa(@Param("pessoaId") Long pessoaId);
}
