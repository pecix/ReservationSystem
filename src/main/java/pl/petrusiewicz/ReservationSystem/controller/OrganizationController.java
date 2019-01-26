package pl.petrusiewicz.ReservationSystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.petrusiewicz.ReservationSystem.error.ErrorMessage;
import pl.petrusiewicz.ReservationSystem.model.Organization;
import pl.petrusiewicz.ReservationSystem.service.OrganizationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    private final OrganizationService service;

    private OrganizationController(OrganizationService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.status(200).body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable int id) {
        var organization = service.getById(id);
        return organization.isPresent()
                ? ResponseEntity.status(200).body(organization.get())
                : ResponseEntity.status(404).body(new ErrorMessage("Organization ID: " + id + " don't exist."));
    }

    @GetMapping(params = "name")
    public ResponseEntity getByName(@RequestParam String name) {
        var organization = service.getByName(name);
        return organization.isPresent()
                ? ResponseEntity.status(200).body(organization.get())
                : ResponseEntity.status(404).body(new ErrorMessage("Organization " + name + " don't exist."));
    }

    @PostMapping
    public ResponseEntity add(@Valid @RequestBody Organization organization) {
        organization.setName(organization.getName().trim());
        var organizationFromDb = service.getByName(organization.getName());
        return organizationFromDb.isEmpty()
                ? ResponseEntity.status(201).body(service.add(organization))
                : ResponseEntity.status(406).body(new ErrorMessage("Organization " + organization.getName() + " already exist."));
    }

    @DeleteMapping
    public ResponseEntity removeAll() {
        service.removeAll();
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable int id) {
        var organization = service.getById(id);
        if (organization.isPresent()) {
            service.remove(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).body(new ErrorMessage("Organization ID: " + id + " don't exist."));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable int id, @Valid @RequestBody Organization organization) {
        var organizationFromDb = service.getById(id);
        if (organizationFromDb.isPresent()) {
            service.update(id, organization);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).body(new ErrorMessage("Organization ID: " + id + " don't exist"));
    }
}
