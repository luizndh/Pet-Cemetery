package com.petcemetery.petcemetery.pet;

import org.springframework.stereotype.Service;

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
