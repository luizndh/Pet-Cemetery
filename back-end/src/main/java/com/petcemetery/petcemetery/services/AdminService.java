package com.petcemetery.petcemetery.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.petcemetery.petcemetery.dto.HistoricoJazigoDTO;
import com.petcemetery.petcemetery.model.Admin;
import com.petcemetery.petcemetery.model.Jazigo;
import com.petcemetery.petcemetery.model.Pet;
import com.petcemetery.petcemetery.repositorio.AdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final JazigoService jazigoService;
    private final AdminRepository repository;

    public List<HistoricoJazigoDTO> visualizarHistorico(Long id) {
        Jazigo jazigo = this.jazigoService.findById(id);
        List<HistoricoJazigoDTO> historico = new ArrayList<>();

        if(jazigo != null) {
            for(Pet pet: jazigo.getHistoricoPets()) {
                historico.add(new HistoricoJazigoDTO(id, pet.getNome(), pet.getDataNascimento(), pet.getEspecie(), pet.getProprietario().getNome(), pet.getDataEnterro().toLocalDate(), pet.getDataExumacao().toLocalDate()));
            }

        } else {
            throw new IllegalArgumentException("Jazigo não encontrado");
        }

        return historico;
    }

    public Admin findById(Long id) {
        Optional<Admin> admin = this.repository.findById(id);

        if(admin.isPresent()) {
            return admin.get();
        } else {
            throw new IllegalArgumentException("Admin não encontrado");
        }
    }

    public Admin findByEmail(String email) {
        return this.repository.findByEmail(email);
    }
}
