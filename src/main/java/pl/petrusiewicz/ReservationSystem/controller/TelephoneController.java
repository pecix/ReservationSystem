//package pl.petrusiewicz.ReservationSystem.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import pl.petrusiewicz.ReservationSystem.model.Telephone;
//import pl.petrusiewicz.ReservationSystem.service.TelephoneService;
//
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping("/{organizationName}/{conferenceRoomName}")
//public class TelephoneController {
//
//    @Autowired
//    TelephoneService service;
//
//    @GetMapping("/telephone")
//    public ResponseEntity get(@PathVariable String organizationName, @PathVariable String conferenceRoomName){
//        Telephone telephone = service.get(organizationName, conferenceRoomName);
//        if (telephone != null){
//            return ResponseEntity.ok().body(telephone);
//        } else {
//            return ResponseEntity.badRequest().body("Sala konferencyjna " + conferenceRoomName + " nie posiada telefonu.");
//        }
//    }
//
//    @PostMapping("/telephone")
//    public ResponseEntity add(@PathVariable String organizationName, @PathVariable String conferenceRoomName, @Valid @RequestBody Telephone telephone){
//        Telephone tel = service.get(organizationName, conferenceRoomName);
//        if (tel == null){
//            service.add(organizationName, conferenceRoomName, telephone);
//            return ResponseEntity.status(201).build();
//        } else {
//            return ResponseEntity.badRequest().body("Sala konferencyjna " + conferenceRoomName + " posiada już telefon.");
//        }
//    }
//
//    @DeleteMapping("/telephone")
//    public ResponseEntity remove(@PathVariable String organizationName, @PathVariable String conferenceRoomName){
//        Telephone telephone = service.get(organizationName, conferenceRoomName);
//        if (telephone != null){
//            service.remove(organizationName, conferenceRoomName);
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.badRequest().body("Sala konferencyjna " + conferenceRoomName + " nie posiada telefonu.");
//        }
//    }
//}
