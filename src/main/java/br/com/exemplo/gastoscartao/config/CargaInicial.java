package br.com.exemplo.gastoscartao.config;

import br.com.exemplo.gastoscartao.entity.Pessoa;
import br.com.exemplo.gastoscartao.repository.PessoaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CargaInicial {

    @Bean
    CommandLineRunner carregarUsuarios(PessoaRepository repository) {

        return args -> {

            if (repository.count() == 0) {

                Pessoa p1 = new Pessoa();
                p1.setNome("José Silva");
                p1.setEmail("jose@email.com");
                p1.setTelefone("11999999999");
                p1.setCpf("12345678900");
                p1.setSenha("123456789");

                Pessoa p2 = new Pessoa();
                p2.setNome("Maria Souza");
                p2.setEmail("maria@email.com");
                p2.setTelefone("11888888888");
                p2.setCpf("55555555555");
                p2.setSenha("987654321");

                Pessoa p3 = new Pessoa();
                p3.setNome("João Santos");
                p3.setEmail("joao@email.com");
                p3.setTelefone("11777777777");
                p3.setCpf("11111111111");
                p3.setSenha("191919");

                repository.save(p1);
                repository.save(p2);
                repository.save(p3);

                System.out.println("USUÁRIOS CARREGADOS NO BANCO");
            }
        };
    }
}