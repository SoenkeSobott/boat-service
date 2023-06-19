package com.example.boat;

import com.example.boat.entity.Boat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boats")
public class BoatController {

    private final BoatService service;

    @Autowired
    public BoatController(BoatService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Boat>> getAll() {
        // TODO: Implement pagination to prevent over-fetching.
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boat> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Boat> create(@RequestBody Boat boat) {
        // TODO: Implement validation for the Boat object.
        Boat createdBoat = service.save(boat);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBoat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boat> update(@PathVariable Long id, @RequestBody Boat boat) {
        // TODO: Handle the case where the Boat object is null.
        return ResponseEntity.ok(service.update(id, boat));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // TODO: Handle the case where the id is null.
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}


