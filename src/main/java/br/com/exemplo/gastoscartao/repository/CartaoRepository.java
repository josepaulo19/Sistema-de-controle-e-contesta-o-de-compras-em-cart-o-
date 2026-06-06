package br.com.exemplo.gastoscartao.repository;

import br.com.exemplo.gastoscartao.entity.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    List<Cartao> findByPessoaId(Long pessoaId);
}
