package pl.petrusiewicz.ReservationSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.petrusiewicz.ReservationSystem.model.ConferenceRoom;
import pl.petrusiewicz.ReservationSystem.service.ConferenceRoomService;

import javax.validation.Valid;

@RestController
@RequestMapping("/{organizationName}")
public class ConferenceRoomController {

    @Autowired
    ConferenceRoomService conferenceRoomService;

    @GetMapping("/conferencerooms")
    public ResponseEntity getAll(@PathVariable String organizationName){
        return ResponseEntity.ok(conferenceRoomService.getAll(organizationName));
    }

    @GetMapping("/conferenceroom/id/{id}")
    public ResponseEntity getById(@PathVariable int id){
        ConferenceRoom conferenceRoom = conferenceRoomService.getById(id);
        if (conferenceRoom != null){
            return ResponseEntity.ok().body(conferenceRoom);
        } else {
            return ResponseEntity.badRequest().body("Sala konferencyjna o id: " + id + " nie istnieje");
        }
    }

    @GetMapping("/conferenceroom/{conferenceRoomName}")
    public ResponseEntity findByName(@PathVariable String organizationName, @PathVariable String conferenceRoomName){
        ConferenceRoom conferenceRoom = conferenceRoomService.findByName(organizationName, conferenceRoomName);
        if (conferenceRoom != null){
            return ResponseEntity.ok().body(conferenceRoom);
        } else{
            return ResponseEntity.badRequest().body("Sala konferencyjna o nazwie " + conferenceRoomName + " nie istnieje.");
        }
    }

    @PostMapping("/conferenceroom")
    public ResponseEntity add(@PathVariable String organizationName, @Valid @RequestBody ConferenceRoom conferenceRoom){
        if(!conferenceRoomService.isExist(organizationName, conferenceRoom)){
            conferenceRoomService.addConferenceRoom(organizationName, conferenceRoom);
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.badRequest().body("Sala konferencyjna o nazwie " + conferenceRoom.getName() + " już istnieje.");
    }

    @DeleteMapping("/conferenceroom/{conferenceRoomName}")
    public ResponseEntity remove(@PathVariable String organizationName, @PathVariable String conferenceRoomName){
        ConferenceRoom conferenceRoom = conferenceRoomService.findByName(organizationName, conferenceRoomName);
        if(conferenceRoom != null){
            conferenceRoomService.removeConferenceRoomByName(organizationName, conferenceRoomName);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Sala konferencyjna o nazwie " + conferenceRoomName + " nie istnieje.");
        }
    }


}
