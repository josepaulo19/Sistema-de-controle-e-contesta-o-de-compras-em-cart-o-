package br.com.exemplo.gastoscartao.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record CartaoRequest(
        @NotNull(message = "O ID da pessoa é obrigatório")
        Long pessoaId,

        @NotBlank(message = "O apelido do cartão é obrigatório")
        @Size(max = 80, message = "O apelido deve ter no máximo 80 caracteres")
        String apelido,

        @NotBlank(message = "A bandeira é obrigatória")
        @Size(max = 80, message = "A bandeira deve ter no máximo 80 caracteres")
        String bandeira,

        @NotBlank(message = "Os últimos dígitos são obrigatórios")
        @Pattern(regexp = "\\d{4}", message = "Informe exatamente 4 dígitos")
        String ultimosDigitos,

        @NotNull(message = "O limite é obrigatório")
        @DecimalMin(value = "0.01", message = "O limite deve ser maior que zero")
        BigDecimal limite,

        @NotNull(message = "O dia de fechamento é obrigatório")
        @Min(value = 1, message = "O dia de fechamento deve ser entre 1 e 31")
        @Max(value = 31, message = "O dia de fechamento deve ser entre 1 e 31")
        Integer diaFechamento,

        @NotNull(message = "O dia de vencimento é obrigatório")
        @Min(value = 1, message = "O dia de vencimento deve ser entre 1 e 31")
        @Max(value = 31, message = "O dia de vencimento deve ser entre 1 e 31")
        Integer diaVencimento
) {
}
