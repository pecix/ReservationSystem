package pl.petrusiewicz.ReservationSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.petrusiewicz.ReservationSystem.model.Telephone;
import pl.petrusiewicz.ReservationSystem.repository.TelephoneRepository;

import java.util.List;

@Service
public class TelephoneService {

    @Autowired
    TelephoneRepository repository;

}
