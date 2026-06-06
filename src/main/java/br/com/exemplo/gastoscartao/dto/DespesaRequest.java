package br.com.exemplo.gastoscartao.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DespesaRequest(
        @NotNull(message = "O ID do cartão é obrigatório")
        Long cartaoId,

        @NotBlank(message = "A descrição é obrigatória")
        @Size(max = 140, message = "A descrição deve ter no máximo 140 caracteres")
        String descricao,

        @NotBlank(message = "A categoria é obrigatória")
        @Size(max = 80, message = "A categoria deve ter no máximo 80 caracteres")
        String categoria,

        @NotNull(message = "O valor é obrigatório")
        @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero")
        BigDecimal valor,

        @NotNull(message = "A data da compra é obrigatória")
        LocalDate dataCompra,

        @NotNull(message = "A quantidade de parcelas é obrigatória")
        @Min(value = 1, message = "A quantidade de parcelas deve ser pelo menos 1")
        Integer parcelas
) {
}
