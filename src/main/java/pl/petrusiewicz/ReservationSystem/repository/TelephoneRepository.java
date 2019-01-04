package pl.petrusiewicz.ReservationSystem.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.petrusiewicz.ReservationSystem.model.Telephone;

@Repository
public interface TelephoneRepository extends PagingAndSortingRepository<Telephone, Integer> {

}
