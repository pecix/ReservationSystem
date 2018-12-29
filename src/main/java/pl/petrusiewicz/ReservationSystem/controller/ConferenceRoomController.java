package pl.petrusiewicz.ReservationSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.petrusiewicz.ReservationSystem.service.ConferenceRoomService;
import pl.petrusiewicz.ReservationSystem.service.OrganizationService;

@RestController
@RequestMapping("/conferenceRoom")
public class ConferenceRoomController {

    @Autowired
    ConferenceRoomService conferenceRoomService;
    @Autowired
    OrganizationService organizationService;


}
