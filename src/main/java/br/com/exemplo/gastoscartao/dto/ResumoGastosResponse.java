package br.com.exemplo.gastoscartao.dto;

import java.math.BigDecimal;

public record ResumoGastosResponse(
        Long pessoaId,
        String nomePessoa,
        BigDecimal totalGasto,
        BigDecimal limiteTotal,
        BigDecimal limiteDisponivel,
        Long quantidadeDespesas
) {
}
