package br.com.exemplo.gastoscartao.service;

import br.com.exemplo.gastoscartao.dto.ResumoGastosResponse;
import br.com.exemplo.gastoscartao.entity.Pessoa;
import br.com.exemplo.gastoscartao.repository.DespesaRepository;
import br.com.exemplo.gastoscartao.repository.ResumoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service
public class ResumoService {

    private final PessoaService pessoaService;
    private final DespesaRepository despesaRepository;
    private final ResumoRepository resumoRepository;

    public ResumoService(PessoaService pessoaService, DespesaRepository despesaRepository, ResumoRepository resumoRepository) {
        this.pessoaService = pessoaService;
        this.despesaRepository = despesaRepository;
        this.resumoRepository = resumoRepository;
    }

    @Transactional(readOnly = true)
    public ResumoGastosResponse gerarResumoPorPessoa(Long pessoaId) {
        Pessoa pessoa = pessoaService.buscarEntidadePorId(pessoaId);
        BigDecimal totalGasto = despesaRepository.somarGastosPorPessoa(pessoaId);
        BigDecimal limiteTotal = resumoRepository.somarLimitesPorPessoa(pessoaId);
        BigDecimal limiteDisponivel = limiteTotal.subtract(totalGasto);
        Long quantidadeDespesas = despesaRepository.contarDespesasPorPessoa(pessoaId);
        return new ResumoGastosResponse(pessoa.getId(), pessoa.getNome(), totalGasto, limiteTotal, limiteDisponivel, quantidadeDespesas);
    }
}
