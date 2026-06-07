package br.com.exemplo.gastoscartao.repository;

import br.com.exemplo.gastoscartao.entity.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;

public interface ResumoRepository extends JpaRepository<Cartao, Long> {
    @Query("select coalesce(sum(c.limite), 0) from Cartao c where c.pessoa.id = :pessoaId")
    BigDecimal somarLimitesPorPessoa(@Param("pessoaId") Long pessoaId);
}
