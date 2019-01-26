package pl.petrusiewicz.ReservationSystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.petrusiewicz.ReservationSystem.model.ConferenceRoom;
import pl.petrusiewicz.ReservationSystem.error.ErrorMessage;
import pl.petrusiewicz.ReservationSystem.service.ConferenceRoomService;
import pl.petrusiewicz.ReservationSystem.service.OrganizationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/organizations/{organizationId}")
public class ConferenceRoomController {

    private final OrganizationService organizationService;
    private final ConferenceRoomService conferenceRoomService;

    public ConferenceRoomController(ConferenceRoomService conferenceRoomService, OrganizationService organizationService) {
        this.organizationService = organizationService;
        this.conferenceRoomService = conferenceRoomService;
    }

    @GetMapping("/rooms")
    public ResponseEntity getAll(@PathVariable int organizationId) {
        var organization = organizationService.getById(organizationId);
        return organization.isPresent()
                ? ResponseEntity.status(200).body(conferenceRoomService.getAll(organizationId))
                : ResponseEntity.status(404).body(new ErrorMessage("Organization ID: " + organizationId + " don't exist."));
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity getById(@PathVariable int organizationId, @PathVariable int id) {
        if (organizationService.getById(organizationId).isEmpty())
            return ResponseEntity.status(404).body(new ErrorMessage("Organization ID: " + organizationId + " don't exist."));
        var conferenceRoom = conferenceRoomService.getById(id);
        return conferenceRoom.isPresent()
                ? ResponseEntity.status(200).body(conferenceRoom)
                : ResponseEntity.status(404).body(new ErrorMessage("Conference room ID: " + id + " don't exist."));
    }

    @GetMapping(value = "/rooms", params = "name")
    public ResponseEntity findByName(@PathVariable int organizationId, @RequestParam String name) {
        if (organizationService.getById(organizationId).isEmpty())
            return ResponseEntity.status(404).body(new ErrorMessage("Organization ID: " + organizationId + " don't exist."));
        var conferenceRoom = conferenceRoomService.getByName(organizationId, name);
        return conferenceRoom.isPresent()
                ? ResponseEntity.status(200).body(conferenceRoom)
                : ResponseEntity.status(404).body(new ErrorMessage("Conference room name " + name + " don't exist."));
    }

    @PostMapping("/rooms")
    public ResponseEntity add(@PathVariable int organizationId, @Valid @RequestBody ConferenceRoom conferenceRoom) {
        if (organizationService.getById(organizationId).isEmpty())
            return ResponseEntity.status(404).body(new ErrorMessage("Organization ID: " + organizationId + " don't exist."));
        var room = conferenceRoomService.getByName(organizationId, conferenceRoom.getName());
        return room.isEmpty()
                ? ResponseEntity.status(201).body(conferenceRoomService.addConferenceRoom(organizationId, conferenceRoom))
                : ResponseEntity.status(406).body(new ErrorMessage("Conference room name " + conferenceRoom.getName() + " already exist."));
    }

    @DeleteMapping("/rooms")
    public ResponseEntity removeAll(@PathVariable int organizationId) {
        if (organizationService.getById(organizationId).isEmpty())
            return ResponseEntity.status(404).body(new ErrorMessage("Organization ID: " + organizationId + " don't exist."));
        conferenceRoomService.removeAll(organizationId);
        return ResponseEntity.status(200).build();
    }

    @DeleteMapping("/rooms/{id}")
    public ResponseEntity remove(@PathVariable int organizationId, @PathVariable int id) {
        if (organizationService.getById(organizationId).isEmpty())
            return ResponseEntity.status(404).body(new ErrorMessage("Organization ID: " + organizationId + " don't exist."));
        var conferenceRoom = conferenceRoomService.getById(id);
        if (conferenceRoom.isPresent()) {
            conferenceRoomService.remove(organizationId, id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).body(new ErrorMessage("Conference room ID: " + id + " don't exist."));
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity update(@PathVariable int organizationId, @PathVariable int id, @Valid @RequestBody ConferenceRoom updatedConferenceRoom) {
        if (organizationService.getById(organizationId).isEmpty())
            return ResponseEntity.status(404).body(new ErrorMessage("Organization ID: " + organizationId + " don't exist."));
        var conferenceRoom = conferenceRoomService.getById(id);
        if (conferenceRoom.isPresent()) {
            conferenceRoomService.update(id, updatedConferenceRoom);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).body(new ErrorMessage("Conference room ID: " + id + " don't exist."));
    }
}

