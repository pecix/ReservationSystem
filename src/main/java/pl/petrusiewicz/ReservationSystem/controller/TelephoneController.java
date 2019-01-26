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
        if (conferenceRoomService.getById(roomId).isEmpty())
            return ResponseEntity.status(404).body(new ErrorMessage("Conference room ID: " + roomId + " don't exist."));
        var telephone = telephoneService.get(roomId);
        return telephone.isPresent()
                ? ResponseEntity.status(200).body(telephone)
                : ResponseEntity.status(404).body(new ErrorMessage("The conference room has no telephone."));
    }

    @PostMapping("/telephones")
    public ResponseEntity add(@PathVariable int roomId, @Valid @RequestBody Telephone telephone) {
        if (conferenceRoomService.getById(roomId).isEmpty())
            return ResponseEntity.status(404).body(new ErrorMessage("Conference room ID: " + roomId + " don't exist."));
        var tel = telephoneService.get(roomId);
        return tel.isEmpty()
                ? ResponseEntity.status(201).body(telephoneService.add(roomId, telephone))
                : ResponseEntity.status(406).body(new ErrorMessage("Conference room already has a telephone."));
    }

    @DeleteMapping("/telephones")
    public ResponseEntity remove(@PathVariable int roomId) {
        if (conferenceRoomService.getById(roomId).isEmpty())
            return ResponseEntity.status(404).body(new ErrorMessage("Conference room ID: " + roomId + " don't exist."));
        if (telephoneService.get(roomId).isPresent()) {
            telephoneService.remove(roomId);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).body(new ErrorMessage("The conference room has no telephone."));
    }

    @PutMapping("/telephones")
    public ResponseEntity update(@PathVariable int roomId, @Valid @RequestBody Telephone updatedTelephone) {
        if (conferenceRoomService.getById(roomId).isEmpty())
            return ResponseEntity.status(404).body(new ErrorMessage("Conference room ID: " + roomId + " don't exist."));
        if (telephoneService.get(roomId).isPresent()) {
            telephoneService.update(roomId, updatedTelephone);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).body(new ErrorMessage("The conference room has no telephone."));
    }
}
