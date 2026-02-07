package br.com.unicsul.ecotrack.service;

import br.com.unicsul.ecotrack.model.PontoColeta;
import br.com.unicsul.ecotrack.repository.EnderecoRepository;
import br.com.unicsul.ecotrack.repository.PontoColetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PontoColetaService {

    @Autowired
    private PontoColetaRepository pontoColetaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public java.util.List<PontoColeta> buscarTodos() {
        return pontoColetaRepository.findAll();
    }

    public java.util.Optional<PontoColeta> buscarPorId(Long id) {
        return pontoColetaRepository.findById(id);
    }

    @Transactional
    public void excluir(Long id) {
        pontoColetaRepository.deleteById(id);
    }

    @Transactional
    public PontoColeta salvar(PontoColeta pontoColeta) {
        if (pontoColeta.getEndereco() == null || pontoColeta.getEndereco().getCidade() == null) {
            throw new RuntimeException("Endereço e cidade são obrigatórios.");
        }

        // Validação: verifica se ponto com mesmo nome já existe na mesma cidade (apenas
        // para novos pontos)
        if (pontoColeta.getId() == null) {
            boolean existe = pontoColetaRepository.findAll().stream()
                    .anyMatch(p -> p.getNome().equalsIgnoreCase(pontoColeta.getNome()) &&
                            p.getEndereco() != null &&
                            pontoColeta.getEndereco().getCidade().equalsIgnoreCase(p.getEndereco().getCidade()));

            if (existe) {
                throw new RuntimeException("Já existe um ponto de coleta com este nome nesta cidade.");
            }
        }

        // Salva o endereço primeiro
        enderecoRepository.save(pontoColeta.getEndereco());

        return pontoColetaRepository.save(pontoColeta);
    }
}
