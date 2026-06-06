package br.com.exemplo.gastoscartao.dto;

import java.time.LocalDateTime;

public record PessoaResponse(
        Long id,
        String nome,
        String email,
        String telefone,
        String token,
        LocalDateTime criadoEm
) {
}
