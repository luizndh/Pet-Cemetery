package com.petcemetery.petcemetery.services;

import org.springframework.stereotype.Service;

import com.petcemetery.petcemetery.model.Pet;
import com.petcemetery.petcemetery.repositorio.PetRepository;

@Service
public class PetService {

    private final PetRepository repository;

    public PetService(PetRepository repository) {
        this.repository = repository;
    }

    public void save(Pet pet) {
        this.repository.save(pet);
    }

}
