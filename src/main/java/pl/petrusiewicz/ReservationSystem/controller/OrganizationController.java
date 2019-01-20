package pl.petrusiewicz.ReservationSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.petrusiewicz.ReservationSystem.error.ErrorMessage;
import pl.petrusiewicz.ReservationSystem.model.Organization;
import pl.petrusiewicz.ReservationSystem.service.OrganizationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    @Autowired
    OrganizationService service;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable int id) {
        if (service.existById(id)) {
            return ResponseEntity.ok(service.findById(id));
        } else {
            return ResponseEntity.status(404).body(new ErrorMessage("Organizacja o ID: " + id + " nie istnieje"));
        }
    }

    @GetMapping(params = "name")
    public ResponseEntity findByName(@RequestParam String name) {
        if (service.existByName(name)) {
            return ResponseEntity.ok(service.findByName(name));
        } else {
            return ResponseEntity.status(404).body(new ErrorMessage("Organizacjia o nazwie " + name + " nie istnieje"));
        }
    }

    @PostMapping
    public ResponseEntity add(@Valid @RequestBody Organization organization) {
        organization.setName(organization.getName().trim());
        if (!service.existByName(organization.getName())) {
            return ResponseEntity.status(201).body(service.add(organization));
        } else {
            return ResponseEntity.status(406).body(new ErrorMessage("Organizacja " + organization.getName() + " już istnieje"));
        }
    }

    @DeleteMapping
    public ResponseEntity removeAll() {
        service.removeAll();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable int id) {
        if (service.existById(id)) {
            service.remove(id);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).body(new ErrorMessage("Organizacja o ID: " + id + " nie istnieje"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable int id, @Valid @RequestBody Organization organization) {
        if (service.existById(id)) {
            service.update(id, organization);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(404).body(new ErrorMessage("Organizacja o ID: " + id + " nie istnieje"));
        }
    }


}
