package com.example.boat;

import com.example.boat.entity.Boat;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boats")
public class BoatController {

    private final BoatRepository repository;

    public BoatController(BoatRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Boat> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Boat getById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Boat not found"));
    }

    @PostMapping
    public ResponseEntity<Boat> create(@RequestBody Boat boat) {
        Boat createdBoat = repository.save(boat);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBoat);
    }

    @PutMapping("/{id}")
    public Boat update(@PathVariable Long id, @RequestBody Boat boat) {
        return repository.findById(id)
                .map(existingBoat -> {
                    existingBoat.setName(boat.getName());
                    existingBoat.setDescription(boat.getDescription());
                    return repository.save(existingBoat);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Boat not found"));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Boat not found");
        }
        repository.deleteById(id);
    }
}


