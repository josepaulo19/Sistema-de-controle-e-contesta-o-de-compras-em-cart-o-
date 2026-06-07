package br.com.exemplo.gastoscartao.controller;

import br.com.exemplo.gastoscartao.dto.ResumoGastosResponse;
import br.com.exemplo.gastoscartao.service.ResumoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resumos")
public class ResumoController {

    private final ResumoService resumoService;

    public ResumoController(ResumoService resumoService) {
        this.resumoService = resumoService;
    }

    @GetMapping("/pessoas/{pessoaId}")
    public ResponseEntity<ResumoGastosResponse> gerarResumoPorPessoa(@PathVariable Long pessoaId) {
        return ResponseEntity.ok(resumoService.gerarResumoPorPessoa(pessoaId));
    }
}
