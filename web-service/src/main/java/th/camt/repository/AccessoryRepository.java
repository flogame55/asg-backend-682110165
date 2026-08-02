package th.camt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.camt.entity.Accessory;

@Repository
public interface AccessoryRepository extends JpaRepository<Accessory, Long> {
}
