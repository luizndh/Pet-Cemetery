package com.petcemetery.petcemetery.usuario.admin;

import java.util.List;
import java.util.stream.Collectors;

import com.petcemetery.petcemetery.core.exceptions.ResourceNotFoundException;
import com.petcemetery.petcemetery.jazigo.JazigoService;
import com.petcemetery.petcemetery.jazigo.dto.HistoricoJazigoDTO;
import com.petcemetery.petcemetery.jazigo.Jazigo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    private final JazigoService jazigoService;
    private final AdminRepository repository;

    public List<HistoricoJazigoDTO> visualizarHistorico(Long id) {
        log.debug("Visualizando histórico do jazigo ID: {}", id);
        Jazigo jazigo = jazigoService.findById(id);

        return jazigo.getHistoricoPets().stream()
                .map(pet -> new HistoricoJazigoDTO(
                        id,
                        pet.getNome(),
                        pet.getDataNascimento(),
                        pet.getEspecie(),
                        pet.getProprietario().getNome(),
                        pet.getDataEnterro().toLocalDate(),
                        pet.getDataExumacao().toLocalDate()))
                .collect(Collectors.toList());
    }

    public Admin findById(Long id) {
        log.debug("Buscando admin com ID: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", id));
    }

    public Admin findByEmail(String email) {
        log.debug("Buscando admin por email: {}", email);
        return repository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Admin", "email", email));
    }
}
