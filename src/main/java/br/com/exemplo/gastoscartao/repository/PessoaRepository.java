package br.com.exemplo.gastoscartao.repository;

import br.com.exemplo.gastoscartao.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    boolean existsByEmail(String email);
    Optional<Pessoa> findByEmail(String email);
    Optional<Pessoa> findByCpf(String cpf);
}
