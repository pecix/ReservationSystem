package pl.petrusiewicz.ReservationSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.petrusiewicz.ReservationSystem.model.Reservation;
import pl.petrusiewicz.ReservationSystem.service.ConferenceRoomService;
import pl.petrusiewicz.ReservationSystem.service.ReservationService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/organizations/{organizationId}/rooms/{roomId}")
public class ReservationController {

    @Autowired
    ReservationService reservationService;
    @Autowired
    ConferenceRoomService conferenceRoomService;

    @GetMapping("/reservations")
    public ResponseEntity findAll(@PathVariable int roomId){
        if (!conferenceRoomService.existById(roomId)){
            return ResponseEntity.badRequest().body("Sala konferencyjna o ID: " + roomId + " nie istnieje");
        }

        return ResponseEntity.ok().body(reservationService.findAll(roomId));
    }

    @GetMapping(value = "/reservations", params = "sort")
    public ResponseEntity findAndSortAll(@PathVariable int roomId, @RequestParam boolean sort){
        if (!conferenceRoomService.existById(roomId)){
            return ResponseEntity.badRequest().body("Sala konferencyjna o ID: " + roomId + " nie istnieje");
        }
        List<Reservation> reservations = reservationService.findAll(roomId);
        if (sort){
            return ResponseEntity.ok().body(reservationService.sort(reservations));
        } else {
            return ResponseEntity.ok().body(reservations);
        }
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity getById(@PathVariable int id){
        Reservation reservation = reservationService.findById(id);
        if (reservation != null){
            return ResponseEntity.ok().body(reservation);
        } else {
            return ResponseEntity.badRequest().body("Rezerwacja o ID: " + id + " nie istnieje.");
        }
    }

    @GetMapping(value = "/reservations", params = "name")
    public ResponseEntity findByName(@PathVariable int roomId, @RequestParam String name){
        if (!conferenceRoomService.existById(roomId)){
            return ResponseEntity.badRequest().body("Sala konferencyjna o ID: " + roomId + " nie istnieje");
        }

        List<Reservation> reservations = reservationService.findAllByName(roomId, name);
        if (!reservations.isEmpty()){
            return ResponseEntity.ok().body(reservations);
        } else {
            return ResponseEntity.badRequest().body("Sala konferencyjna " + conferenceRoomService.findById(roomId).getName() + " nie jest zarezerwowana przez " + name);
        }
    }

    @PostMapping("/reservations")
    public ResponseEntity book(@PathVariable int roomId, @Valid @RequestBody Reservation reservation){
        if (!conferenceRoomService.existById(roomId)){
            return ResponseEntity.badRequest().body("Sala konferencyjna o ID: " + roomId + " nie istnieje");
        }

        if (reservationService.checkAvailability(roomId, reservation)){
            reservationService.book(roomId, reservation);
            return ResponseEntity.status(201).build();
        } else {
            return ResponseEntity.badRequest().body("Sala konferencyjna " + conferenceRoomService.findById(roomId).getName() + " jest zajęta w tym terminie");
        }
    }

    @DeleteMapping(value = "/reservations", params = "name")
    public ResponseEntity cancelAllByName(@PathVariable int roomId, @RequestParam String name){
        if (!conferenceRoomService.existById(roomId)){
            return ResponseEntity.badRequest().body("Sala konferencyjna o ID: " + roomId + " nie istnieje");
        }

        List<Reservation> reservations = reservationService.findAllByName(roomId, name);
        if (!reservations.isEmpty()){
            reservationService.cancelAllByName(roomId, name);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Sala konferencyjna " + conferenceRoomService.findById(roomId).getName() + " nie jest zarezerwowana przez " + name);
        }
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity cancelById(@PathVariable int roomId, @PathVariable int id){
        if (!conferenceRoomService.existById(roomId)){
            return ResponseEntity.badRequest().body("Sala konferencyjna o ID: " + roomId + " nie istnieje");
        }

        Reservation reservation = reservationService.findById(id);
        if (reservation != null){
            reservationService.cancelById(roomId, id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Rezerwacja o ID: " + id + " nie istnieje");
        }
    }

    @DeleteMapping("/reservations")
    public ResponseEntity cancelAll(@PathVariable int roomId){
        if (!conferenceRoomService.existById(roomId)){
            return ResponseEntity.badRequest().body("Sala konferencyjna o ID: " + roomId + " nie istnieje");
        }

        reservationService.cancelAll(roomId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reservations/{id}")
    public ResponseEntity update(@PathVariable int id, @Valid @RequestBody Reservation updatedReservation){
        Reservation reservation = reservationService.findById(id);
        if (reservation != null) {
            reservationService.update(id, updatedReservation);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Rezerwacja o ID: " + id + " nie istnieje");
        }
    }

}
