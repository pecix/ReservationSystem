package pl.petrusiewicz.ReservationSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.petrusiewicz.ReservationSystem.model.ConferenceRoom;
import pl.petrusiewicz.ReservationSystem.model.Organization;
import pl.petrusiewicz.ReservationSystem.service.OrganizationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    OrganizationService service;

    @GetMapping("/get")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable int id) {
        int size = service.getAll().size();
        if (id > 0 && id <= size) {
            return ResponseEntity.ok(service.get(id));
        } else {
            return ResponseEntity.badRequest().body("Nie ma organizacji o id: " + id);
        }
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Organization organization) {
        organization.setName(organization.getName().trim());
        if (!service.isExist(organization.getName())) {
            service.add(organization);
            return ResponseEntity.status(201).build();
        } else {
            return ResponseEntity.badRequest().body("Organizacja już istnieje");
        }
    }

    @GetMapping("/find/{name}")
    public ResponseEntity findByName(@PathVariable String name){
        if (service.isExist(name)){
            return ResponseEntity.ok(service.findByName(name));
        } else {
            return ResponseEntity.badRequest().body("Nie ma organizacji o nazwie " + name);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable int id){
        service.remove(id);
        return ResponseEntity.ok().build();
    }



}
