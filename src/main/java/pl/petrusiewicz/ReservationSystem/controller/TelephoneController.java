package pl.petrusiewicz.ReservationSystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.petrusiewicz.ReservationSystem.error.ErrorMessage;
import pl.petrusiewicz.ReservationSystem.model.Telephone;
import pl.petrusiewicz.ReservationSystem.service.ConferenceRoomService;
import pl.petrusiewicz.ReservationSystem.service.TelephoneService;

import javax.validation.Valid;

@RestController
@RequestMapping("/organizations/{organizationId}/rooms/{roomId}")
public class TelephoneController {

    private final ConferenceRoomService conferenceRoomService;
    private final TelephoneService telephoneService;

    public TelephoneController(ConferenceRoomService conferenceRoomService, TelephoneService telephoneService){
        this.conferenceRoomService = conferenceRoomService;
        this.telephoneService = telephoneService;
    }


    @GetMapping("/telephones")
    public ResponseEntity get(@PathVariable int roomId) {
        if (!conferenceRoomService.existById(roomId)) {
            return ResponseEntity.status(404).body(new ErrorMessage("Sala konferencyjna o ID: " + roomId + " nie istnieje"));
        }

        var telephone = telephoneService.get(roomId);
        if (telephone != null) {
            return ResponseEntity.ok().body(telephone);
        } else {
            return ResponseEntity.status(404).body(new ErrorMessage("Sala konferencyjna " + conferenceRoomService.findById(roomId).getName() + " nie posiada telefonu."));
        }
    }

    @PostMapping("/telephones")
    public ResponseEntity add(@PathVariable int roomId, @Valid @RequestBody Telephone telephone) {
        if (!conferenceRoomService.existById(roomId)) {
            return ResponseEntity.status(404).body(new ErrorMessage("Sala konferencyjna o ID: " + roomId + " nie istnieje"));
        }

        var tel = telephoneService.get(roomId);
        if (tel == null) {
            return ResponseEntity.status(201).body(telephoneService.add(roomId, telephone));
        } else {
            return ResponseEntity.status(406).body(new ErrorMessage("Sala konferencyjna " + conferenceRoomService.findById(roomId).getName() + " posiada już telefon."));
        }
    }

    @DeleteMapping("/telephones")
    public ResponseEntity remove(@PathVariable int roomId) {
        if (!conferenceRoomService.existById(roomId)) {
            return ResponseEntity.status(404).body(new ErrorMessage("Sala konferencyjna o ID: " + roomId + " nie istnieje"));
        }

        var telephone = telephoneService.get(roomId);
        if (telephone != null) {
            telephoneService.remove(roomId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(404).body(new ErrorMessage("Sala konferencyjna " + conferenceRoomService.findById(roomId).getName() + " nie posiada telefonu."));
        }
    }

    @PutMapping("/telephones")
    public ResponseEntity update(@PathVariable int roomId, @Valid @RequestBody Telephone updatedTelephone) {
        if (!conferenceRoomService.existById(roomId)) {
            return ResponseEntity.status(404).body(new ErrorMessage("Sala konferencyjna o ID: " + roomId + " nie istnieje"));
        }

        var telephone = telephoneService.get(roomId);
        if (telephone != null) {
            telephoneService.update(roomId, updatedTelephone);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(404).body(new ErrorMessage("Sala konferencyjna " + conferenceRoomService.findById(roomId).getName() + " nie posiada telefonu."));
        }
    }
}
