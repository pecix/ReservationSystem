package pl.petrusiewicz.ReservationSystem.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.petrusiewicz.ReservationSystem.model.Reservation;

import java.util.List;

@Repository
public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Integer> {

    Reservation findById(int id);

}
