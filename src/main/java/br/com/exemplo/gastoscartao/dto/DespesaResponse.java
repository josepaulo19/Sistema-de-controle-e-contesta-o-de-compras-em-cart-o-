package br.com.exemplo.gastoscartao.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record DespesaResponse(
        Long id,
        Long cartaoId,
        String apelidoCartao,
        String descricao,
        String categoria,
        BigDecimal valor,
        LocalDate dataCompra,
        Integer parcelas,
        LocalDateTime criadoEm
) {
}
