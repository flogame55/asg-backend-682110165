package th.camt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.camt.entity.SerialPlate;

@Repository
public interface SerialPlateRepository extends JpaRepository<SerialPlate, Long> {
}
