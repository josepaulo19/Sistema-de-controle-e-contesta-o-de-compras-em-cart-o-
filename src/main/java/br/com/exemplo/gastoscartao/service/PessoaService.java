package br.com.exemplo.gastoscartao.service;

import br.com.exemplo.gastoscartao.dto.PessoaRequest;
import br.com.exemplo.gastoscartao.dto.PessoaResponse;
import br.com.exemplo.gastoscartao.entity.Pessoa;
import br.com.exemplo.gastoscartao.exception.RecursoNaoEncontradoException;
import br.com.exemplo.gastoscartao.exception.RegraNegocioException;
import br.com.exemplo.gastoscartao.repository.PessoaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional
    public PessoaResponse criar(PessoaRequest request) {
        if (pessoaRepository.existsByEmail(request.email())) {
            throw new RegraNegocioException("Já existe uma pessoa cadastrada com este e-mail.");
        }

        Pessoa pessoa = new Pessoa(request.nome(), request.email(), request.telefone());
        pessoa.setToken("SEC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return toResponse(pessoaRepository.save(pessoa));
    }

    @Transactional(readOnly = true)
    public List<PessoaResponse> listar() {
        return pessoaRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public PessoaResponse buscarPorId(Long id) {
        return toResponse(buscarEntidadePorId(id));
    }

    @Transactional
    public PessoaResponse atualizar(Long id, PessoaRequest request) {
        Pessoa pessoa = buscarEntidadePorId(id);
        pessoaRepository.findByEmail(request.email())
                .filter(p -> !p.getId().equals(id))
                .ifPresent(p -> { throw new RegraNegocioException("Já existe outra pessoa cadastrada com este e-mail."); });

        pessoa.setNome(request.nome());
        pessoa.setEmail(request.email());
        pessoa.setTelefone(request.telefone());
        return toResponse(pessoa);
    }

    @Transactional
    public void excluir(Long id) {
        Pessoa pessoa = buscarEntidadePorId(id);
        pessoaRepository.delete(pessoa);
    }

    public Pessoa buscarEntidadePorId(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa não encontrada com ID: " + id));
    }

    private PessoaResponse toResponse(Pessoa pessoa) {
        return new PessoaResponse(pessoa.getId(), pessoa.getNome(), pessoa.getEmail(), pessoa.getTelefone(), pessoa.getToken(), pessoa.getCriadoEm());
    }
}
