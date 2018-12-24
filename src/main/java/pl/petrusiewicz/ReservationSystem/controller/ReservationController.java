package pl.petrusiewicz.ReservationSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import pl.petrusiewicz.ReservationSystem.service.ConferenceRoomService;
import pl.petrusiewicz.ReservationSystem.service.OrganizationService;

public class ReservationController {

    @Autowired
    OrganizationService organizationService;
    @Autowired
    ConferenceRoomService conferenceRoomService;


}
