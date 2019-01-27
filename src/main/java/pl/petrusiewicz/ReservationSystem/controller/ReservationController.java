package pl.petrusiewicz.ReservationSystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.petrusiewicz.ReservationSystem.error.ErrorMessage;
import pl.petrusiewicz.ReservationSystem.model.Reservation;
import pl.petrusiewicz.ReservationSystem.service.ConferenceRoomService;
import pl.petrusiewicz.ReservationSystem.service.ReservationService;

import javax.validation.Valid;


@RestController
@RequestMapping("/organizations/{organizationId}/rooms/{roomId}")
public class ReservationController {

    private final ConferenceRoomService conferenceRoomService;
    private final ReservationService reservationService;

    public ReservationController(ConferenceRoomService conferenceRoomService, ReservationService reservationService) {
        this.conferenceRoomService = conferenceRoomService;
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations")
    public ResponseEntity getAll(@PathVariable int organizationId, @PathVariable int roomId) {
        if (conferenceRoomService.checkIsOrganizationNotContainsConferenceRoom(organizationId, roomId))
            return ResponseEntity.status(404).body(new ErrorMessage("Bad Organization ID or Conference Room ID"));
        return ResponseEntity.status(200).body(reservationService.getAll(roomId));
    }

    @GetMapping(value = "/reservations", params = "sort")
    public ResponseEntity getAndSortAll(@PathVariable int organizationId, @PathVariable int roomId, @RequestParam boolean sort) {
        if (conferenceRoomService.checkIsOrganizationNotContainsConferenceRoom(organizationId, roomId))
            return ResponseEntity.status(404).body(new ErrorMessage("Bad Organization ID or Conference Room ID"));
        return sort
                ? ResponseEntity.status(200).body(reservationService.sortByBeginReservation(roomId))
                : ResponseEntity.status(200).body(reservationService.getAll(roomId));
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity getById(@PathVariable int organizationId, @PathVariable int roomId, @PathVariable int id) {
        if (conferenceRoomService.checkIsOrganizationNotContainsConferenceRoom(organizationId, roomId))
            return ResponseEntity.status(404).body(new ErrorMessage("Bad Organization ID or Conference Room ID"));
        var reservation = reservationService.getById(id);
        return reservation.isPresent()
                ? ResponseEntity.status(200).body(reservation)
                : ResponseEntity.status(404).body(new ErrorMessage("Reservation ID: " + id + " dont'exist."));
    }

    @GetMapping(value = "/reservations", params = "name")
    public ResponseEntity getByName(@PathVariable int organizationId, @PathVariable int roomId, @RequestParam String name) {
        if (conferenceRoomService.checkIsOrganizationNotContainsConferenceRoom(organizationId, roomId))
            return ResponseEntity.status(404).body(new ErrorMessage("Bad Organization ID or Conference Room ID"));
        var reservations = reservationService.getAllByName(roomId, name);
        return !reservations.isEmpty()
                ? ResponseEntity.status(200).body(reservations)
                : ResponseEntity.status(406).body(new ErrorMessage("Conference room is not reserved by " + name + "."));

    }

    @PostMapping("/reservations")
    public ResponseEntity book(@PathVariable int organizationId, @PathVariable int roomId, @Valid @RequestBody Reservation reservation) {
        if (conferenceRoomService.checkIsOrganizationNotContainsConferenceRoom(organizationId, roomId))
            return ResponseEntity.status(404).body(new ErrorMessage("Bad Organization ID or Conference Room ID"));
        if (!reservationService.checkTimeRestriction(reservation))
            return ResponseEntity.status(406).body(new ErrorMessage("Booking should have a minimum of "
                    + reservationService.getMinBookingTime() + " minutes and a maximum of "
                    + reservationService.getMaxBookingTime() + " minutes."));
        return !reservationService.isAlreadyReserved(roomId, reservation)
                ? ResponseEntity.status(201).body(reservationService.book(roomId, reservation))
                : ResponseEntity.status(406).body(new ErrorMessage("Conference room is reserved at this time."));
    }

    @DeleteMapping(value = "/reservations", params = "name")
    public ResponseEntity cancelAllByName(@PathVariable int organizationId, @PathVariable int roomId, @RequestParam String name) {
        if (conferenceRoomService.checkIsOrganizationNotContainsConferenceRoom(organizationId, roomId))
            return ResponseEntity.status(404).body(new ErrorMessage("Bad Organization ID or Conference Room ID"));
        if (!reservationService.getAllByName(roomId, name).isEmpty()) {
            reservationService.cancelAllByName(roomId, name);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(406).body(new ErrorMessage("Conference room is not reserved by " + name + "."));
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity cancelById(@PathVariable int organizationId, @PathVariable int roomId, @PathVariable int id) {
        if (conferenceRoomService.checkIsOrganizationNotContainsConferenceRoom(organizationId, roomId))
            return ResponseEntity.status(404).body(new ErrorMessage("Bad Organization ID or Conference Room ID"));
        var reservation = reservationService.getById(id);
        if (reservation.isPresent()) {
            reservationService.cancelById(roomId, id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(404).body(new ErrorMessage("Reservation ID: " + id + " dont't exist."));
    }

    @DeleteMapping("/reservations")
    public ResponseEntity cancelAll(@PathVariable int organizationId, @PathVariable int roomId) {
        if (conferenceRoomService.checkIsOrganizationNotContainsConferenceRoom(organizationId, roomId))
            return ResponseEntity.status(404).body(new ErrorMessage("Bad Organization ID or Conference Room ID"));
        reservationService.cancelAll(roomId);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("/reservations/{id}")
    public ResponseEntity update(@PathVariable int organizationId, @PathVariable int roomId, @PathVariable int id, @Valid @RequestBody Reservation updatedReservation) {
        if (conferenceRoomService.checkIsOrganizationNotContainsConferenceRoom(organizationId, roomId))
            return ResponseEntity.status(404).body(new ErrorMessage("Bad Organization ID or Conference Room ID"));
        if (reservationService.getById(id).isPresent()) {
            reservationService.update(id, updatedReservation);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).body(new ErrorMessage("Reservation ID: " + id + " dont't exist."));
    }
}
