package pl.petrusiewicz.ReservationSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.petrusiewicz.ReservationSystem.model.Reservation;
import pl.petrusiewicz.ReservationSystem.service.ReservationService;

import javax.validation.Valid;


@RestController
@RequestMapping("/{organizationName}/{conferenceRoomName}")
public class ReservationController {

    @Autowired
    ReservationService service;

    @GetMapping("/reservations")
    public ResponseEntity getAll(@PathVariable String organizationName, @PathVariable String conferenceRoomName){
        return ResponseEntity.ok().body(service.getAll(organizationName, conferenceRoomName));
    }

    @GetMapping("/reservation/id/{id}")
    public ResponseEntity getById(@PathVariable int id){
        Reservation reservation = service.getById(id);
        if (reservation != null){
            return ResponseEntity.ok().body(reservation);
        } else {
            return ResponseEntity.badRequest().body("Rezerwacja o ID: " + id + " nie istnieje.");
        }
    }

    @GetMapping("/reservation/{reservingName}")
    public ResponseEntity findByName(@PathVariable String organizationName, @PathVariable String conferenceRoomName, @PathVariable String reservingName){
        Reservation reservation = service.findByName(organizationName, conferenceRoomName, reservingName);
        if (reservation != null){
            return ResponseEntity.ok().body(reservation);
        } else {
            return ResponseEntity.badRequest().body("Sala konferencyjna " + conferenceRoomName + " nie jest zarezerwowana przez " + reservingName);
        }
    }

    @PostMapping("/reservation")
    public ResponseEntity book(@PathVariable String organizationName, @PathVariable String conferenceRoomName, @Valid @RequestBody Reservation reservation){
        service.book(organizationName, conferenceRoomName, reservation);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/reservation/{reservingName}")
    public ResponseEntity cancel(@PathVariable String organizationName, @PathVariable String conferenceRoomName, @PathVariable String reservingName){
        Reservation reservation = service.findByName(organizationName, conferenceRoomName, reservingName);
        if (reservation != null){
            service.cancel(organizationName, conferenceRoomName, reservingName);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Sala konferencyjna " + conferenceRoomName + " nie jest zarezerwowana przez " + reservingName);
        }
    }

}
