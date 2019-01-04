package pl.petrusiewicz.ReservationSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        if (service.isExist(id)) {
            return ResponseEntity.ok(service.get(id));
        } else {
            return ResponseEntity.badRequest().body("Organizacja o ID: " + id + " nie istnieje");
        }
    }

    @GetMapping(params = "name")
    public ResponseEntity findByName(@RequestParam String name){
        if (service.isExistByName(name)) {
            return ResponseEntity.ok(service.findByName(name));
        } else {
            return ResponseEntity.badRequest().body("Nie ma organizacji o nazwie " + name);
        }
    }

    @PostMapping
    public ResponseEntity add(@Valid @RequestBody Organization organization) {
        organization.setName(organization.getName().trim());
        if (!service.isExistByName(organization.getName())) {
            service.add(organization);
            return ResponseEntity.status(201).build();
        } else {
            return ResponseEntity.badRequest().body("Organizacja " + organization.getName() + " już istnieje");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable int id){
        service.remove(id);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable int id, @Valid @RequestBody Organization organization){
        if (service.isExist(id)){
            service.update(id, organization);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Organizacja o ID: " + id + " nie istnieje");
        }
    }



}
