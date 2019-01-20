package pl.petrusiewicz.ReservationSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.petrusiewicz.ReservationSystem.model.ConferenceRoom;
import pl.petrusiewicz.ReservationSystem.model.ErrorMessage;
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
        if (!organizationService.existById(organizationId)) {
            return ResponseEntity.status(406).body(new ErrorMessage("Organizacja o ID: " + organizationId + " nie istnieje"));
        }

        return ResponseEntity.ok(conferenceRoomService.findAll(organizationId));
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity findById(@PathVariable int organizationId, @PathVariable int id) {
        if (!organizationService.existById(organizationId)) {
            return ResponseEntity.status(406).body(new ErrorMessage("Organizacja o ID: " + organizationId + " nie istnieje"));
        }

        var conferenceRoom = conferenceRoomService.findById(id);
        if (conferenceRoom != null) {
            return ResponseEntity.ok().body(conferenceRoom);
        } else {
            return ResponseEntity.status(406).body(new ErrorMessage("Sala konferencyjna o id: " + id + " nie istnieje"));
        }
    }

    @GetMapping(value = "/rooms", params = "name")
    public ResponseEntity findByName(@PathVariable int organizationId, @RequestParam String name) {
        if (!organizationService.existById(organizationId)) {
            return ResponseEntity.status(406).body(new ErrorMessage("Organizacja o ID: " + organizationId + " nie istnieje"));
        }

        var conferenceRoom = conferenceRoomService.findByName(organizationId, name);
        if (conferenceRoom != null) {
            return ResponseEntity.ok().body(conferenceRoom);
        } else {
            return ResponseEntity.status(406).body(new ErrorMessage("Sala konferencyjna o nazwie " + name + " nie istnieje."));
        }
    }

    @PostMapping("/rooms")
    public ResponseEntity add(@PathVariable int organizationId, @Valid @RequestBody ConferenceRoom conferenceRoom) {
        if (!organizationService.existById(organizationId)){
            return ResponseEntity.status(406).body(new ErrorMessage("Organizacja o ID: " +  organizationId + " nie istnieje"));
        }

        if (!conferenceRoomService.existByName(organizationId, conferenceRoom.getName())) {
            conferenceRoomService.addConferenceRoom(organizationId, conferenceRoom);
            return ResponseEntity.status(201).body(conferenceRoom);
        } else {
            return ResponseEntity.status(406).body(new ErrorMessage("Sala konferencyjna o nazwie " + conferenceRoom.getName() + " już istnieje."));
        }

    }

    @DeleteMapping("/rooms")
    public ResponseEntity removeAll(@PathVariable int organizationId){
        if (!organizationService.existById(organizationId)){
            return ResponseEntity.status(406).body(new ErrorMessage("Organizacja o ID: " +  organizationId + " nie istnieje"));
        }

        conferenceRoomService.removeAll(organizationId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/rooms/{id}")
    public ResponseEntity remove(@PathVariable int organizationId, @PathVariable int id) {
        if (!organizationService.existById(organizationId)){
            return ResponseEntity.status(406).body(new ErrorMessage("Organizacja o ID: " +  organizationId + " nie istnieje"));
        }

        var conferenceRoom = conferenceRoomService.findById(id);
        if (conferenceRoom != null) {
            conferenceRoomService.remove(organizationId, id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(406).body(new ErrorMessage("Sala konferencyjna o ID: " + id + " nie istnieje."));
        }
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity update(@PathVariable int organizationId, @PathVariable int id, @Valid @RequestBody ConferenceRoom updatedConferenceRoom) {
        if (!organizationService.existById(organizationId)){
            return ResponseEntity.status(406).body(new ErrorMessage("Organizacja o ID: " +  organizationId + " nie istnieje"));
        }

        var conferenceRoom = conferenceRoomService.findById(id);
        if (conferenceRoom != null) {
            conferenceRoomService.update(id, updatedConferenceRoom);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(406).body(new ErrorMessage("Sala konferencyjna o ID: " + id + " nie istnieje."));
        }
    }


}
