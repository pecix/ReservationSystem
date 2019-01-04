package pl.petrusiewicz.ReservationSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.petrusiewicz.ReservationSystem.model.ConferenceRoom;
import pl.petrusiewicz.ReservationSystem.service.ConferenceRoomService;
import pl.petrusiewicz.ReservationSystem.service.OrganizationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/organizations/{organizationId}")
public class ConferenceRoomController {

    @Autowired
    ConferenceRoomService conferenceRoomService;
    @Autowired
    OrganizationService organizationService;

    @GetMapping("/rooms")
    public ResponseEntity findAll(@PathVariable int organizationId) {
        if (!organizationService.isExist(organizationId)) {
            return ResponseEntity.badRequest().body("Organizacja o ID: " + organizationId + " nie istnieje");
        }

        return ResponseEntity.ok(conferenceRoomService.findAll(organizationId));
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity findById(@PathVariable int organizationId, @PathVariable int id) {
        if (!organizationService.isExist(organizationId)) {
            return ResponseEntity.badRequest().body("Organizacja o ID: " + organizationId + " nie istnieje");
        }

        ConferenceRoom conferenceRoom = conferenceRoomService.findById(id);
        if (conferenceRoom != null) {
            return ResponseEntity.ok().body(conferenceRoom);
        } else {
            return ResponseEntity.badRequest().body("Sala konferencyjna o id: " + id + " nie istnieje");
        }
    }

    @GetMapping(value = "/rooms", params = "name")
    public ResponseEntity findByName(@PathVariable int organizationId, @RequestParam String name) {
        if (!organizationService.isExist(organizationId)) {
            return ResponseEntity.badRequest().body("Organizacja o ID: " + organizationId + " nie istnieje");
        }

        ConferenceRoom conferenceRoom = conferenceRoomService.findByName(organizationId, name);
        if (conferenceRoom != null) {
            return ResponseEntity.ok().body(conferenceRoom);
        } else {
            return ResponseEntity.badRequest().body("Sala konferencyjna o nazwie " + name + " nie istnieje.");
        }
    }

    @PostMapping("/rooms")
    public ResponseEntity add(@PathVariable int organizationId, @Valid @RequestBody ConferenceRoom conferenceRoom) {
        if (!organizationService.isExist(organizationId)){
            return ResponseEntity.badRequest().body("Organizacja o ID: " +  organizationId + " nie istnieje");
        }

        if (!conferenceRoomService.isExistByName(organizationId, conferenceRoom)) {
            conferenceRoomService.addConferenceRoom(organizationId, conferenceRoom);
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.badRequest().body("Sala konferencyjna o nazwie " + conferenceRoom.getName() + " już istnieje.");
    }

    @DeleteMapping("/rooms/{id}")
    public ResponseEntity remove(@PathVariable int organizationId, @PathVariable int id) {
        if (!organizationService.isExist(organizationId)){
            return ResponseEntity.badRequest().body("Organizacja o ID: " +  organizationId + " nie istnieje");
        }

        ConferenceRoom conferenceRoom = conferenceRoomService.findById(id);
        if (conferenceRoom != null) {
            conferenceRoomService.remove(organizationId, id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Sala konferencyjna o ID: " + id + " nie istnieje.");
        }
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity update(@PathVariable int organizationId, @PathVariable int id, @Valid @RequestBody ConferenceRoom updatedConferenceRoom) {
        if (!organizationService.isExist(organizationId)){
            return ResponseEntity.badRequest().body("Organizacja o ID: " +  organizationId + " nie istnieje");
        }

        ConferenceRoom conferenceRoom = conferenceRoomService.findById(id);
        if (conferenceRoom != null) {
            conferenceRoomService.update(id, updatedConferenceRoom);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Sala konferencyjna o ID: " + id + " nie istnieje.");
        }
    }


}
