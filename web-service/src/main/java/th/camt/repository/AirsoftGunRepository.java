package th.camt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.camt.entity.AirsoftGun;

@Repository
public interface AirsoftGunRepository extends JpaRepository<AirsoftGun, Long> {
}
