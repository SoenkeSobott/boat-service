package com.example.boat;

import com.example.boat.entity.Boat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class BoatService {

    private final BoatRepository repository;

    @Autowired
    public BoatService(BoatRepository repository) {
        this.repository = repository;
    }

    public List<Boat> findAll() {
        return repository.findAll();
    }

    public Boat findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Boat not found"));
    }

    public Boat save(Boat boat) {
        return repository.save(boat);
    }

    public Boat update(Long id, Boat boat) {
        return repository.findById(id)
                .map(existingBoat -> {
                    existingBoat.setName(boat.getName());
                    existingBoat.setDescription(boat.getDescription());
                    return repository.save(existingBoat);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Boat not found"));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Boat not found");
        }
        repository.deleteById(id);
    }
}