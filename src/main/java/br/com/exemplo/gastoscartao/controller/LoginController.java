package br.com.exemplo.gastoscartao.controller;

import br.com.exemplo.gastoscartao.dto.LoginRequest;
import br.com.exemplo.gastoscartao.entity.Pessoa;
import br.com.exemplo.gastoscartao.repository.PessoaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final PessoaRepository pessoaRepository;

    public LoginController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

   @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

    String cpf = request.cpf().replaceAll("[^0-9]", "");

    Pessoa pessoa = pessoaRepository
            .findByCpf(cpf)
            .orElse(null);

    if (pessoa == null) {
        return ResponseEntity.status(401)
                .body("CPF ou senha inválidos");
    }

    if (!pessoa.getSenha().equals(request.senha())) {
        return ResponseEntity.status(401)
                .body("CPF ou senha inválidos");
    }

    return ResponseEntity.ok(
            java.util.Map.of(
                    "nome", pessoa.getNome(),
                    "cpf", pessoa.getCpf()
            )
    );
    }
 }
