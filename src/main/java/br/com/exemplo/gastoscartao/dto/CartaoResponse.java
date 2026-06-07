package br.com.exemplo.gastoscartao.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CartaoResponse(
        Long id,
        Long pessoaId,
        String nomePessoa,
        String apelido,
        String bandeira,
        String ultimosDigitos,
        BigDecimal limite,
        Integer diaFechamento,
        Integer diaVencimento,
        LocalDateTime criadoEm
) {
}
